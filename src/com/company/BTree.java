package com.company;

public class BTree {
    private BNode root;
    private int order;

    BTree(int order) {
        this.order = order;
        this.root = new BNode(this.order);
    }

    public BNode getRoot() {
        return this.root;
    }

    public BNode search(int key) {
        BNode currentNode = root;
        while (currentNode.findAppropriateChild(key) != null) {
            currentNode = currentNode.findAppropriateChild(key);
        }
        return currentNode;
    }

    public void printTree(BNode root, int level) {
        if (root == null) return;
        System.out.println(level);
        root.printElements();
        System.out.print("\n");
        level += 1;
        if (!root.isEmpty()) {
            for (int i = 0; i < root.getElementNum(); i++) {
                if (root.getElementByIndex(i).getLeft() != null) {
                    printTree(root.getElementByIndex(i).getLeft(), level);
                }
            }
            if (root.getLast().getRight() != null) {
                printTree(root.getLast().getRight(), level);
            }
        }
    }


    public BNodeKey insert(BNode parent, int key) {
        BNode current = parent.findAppropriateChild(key);
        if (current != null) {
            BNodeKey splitFromBelow = insert(current, key);
            if (splitFromBelow != null) {
                if (parent.isFull() && parent == root) {
                    BNodeKey toAdd = new BNodeKey(parent, splitFromBelow);
                    BNode newRoot = new BNode(order);
                    newRoot.addKeyFromSplit(toAdd);
                    root = newRoot;
                    return null;
                } else if (parent.isFull()) {
                    BNodeKey toReturn = new BNodeKey(parent, splitFromBelow);
                    return toReturn;
                } else {
                    parent.addKeyFromSplit(splitFromBelow);
                    return null;
                }
            } else {
                return null;
            }
        } else {
            if (parent.isFull() && parent == root) {
                BNodeKey toAdd = new BNodeKey();
                BNode newRoot = new BNode(order);
                toAdd.setKey(key);
                BNodeKey forRoot = new BNodeKey(parent, toAdd);
                newRoot.addKeyFromSplit(forRoot);
                root = newRoot;
                return null;
            } else if (parent.isFull()) {
                BNodeKey newKey = new BNodeKey();
                newKey.setKey(key);
                BNodeKey toReturn = new BNodeKey(parent, newKey);
                return toReturn;
            } else {
                parent.addKey(key);
                return null;
            }
        }
    }

    public void delete(int key) {
        BNode parent = root;
        BNode targetNode = root;
        while(targetNode.findAppropriateChild(key) != null) {
            parent = targetNode;
            targetNode = targetNode.findAppropriateChild(key);
        }
        int keyToBorrow = borrowAndDelete(targetNode, key);
        BNodeKey oldKey = targetNode.getKey(key);
        BNode first = oldKey.getLeft();
        BNode second = oldKey.getRight();
        BNodeKey precedingKey = parent.getNextSmallestKey(key);
        BNodeKey succeedingKey = parent.getNextLargestKey(key);

        if(targetNode == root) {
            if(!canMerge(first, second)) {
                // If we cannot merge the nodes below then we need to try something else
                delete(keyToBorrow);
                oldKey.setKey(keyToBorrow);
            } else {
                boolean isSingleItem = (targetNode.getElementNum() == 1);
                targetNode.delete(key);
                BNode mergedChild = null;
                if(first != null && second != null)
                    mergedChild = mergeBranches(first, second);
                setMergedChildOnLeftoverNodes(targetNode, key, mergedChild);
                if (isSingleItem)
                    root = mergedChild;
            }
        } else if (targetNode.isInternal()) {
            if (canMerge(first, second)) {
                // in this case we can simply delete and merge lower nodes where appropriate
                handleMergeableChildrenInternal(targetNode, precedingKey, succeedingKey, key, first, second);
            } else {
                // backtrack recursively and attempt to delete a key lower down for the purposes of putting it in the current key's place
                delete(keyToBorrow);
                oldKey.setKey(keyToBorrow);
            }
        } else if (targetNode.isLeaf()) {
            if (targetNode.getElementNum() <= this.order / 2) {
                if (!isLeafAndBorrowFromLeftSibling(parent, key)) { 
                    if(!isLeafAndBorrowFromRightSibling(parent, key)) {
                        // If these borrow actions cannot be performed then the last course of action is to
                        // temporarily remove either a preceding or succeeding key such that lower nodes are merged
                        int keyToReinsert;
                        keyToReinsert = precedingKey != null ? precedingKey.getKey() : succeedingKey.getKey();
                        delete(keyToReinsert);
                        delete(key);
                        insert(root, keyToReinsert);
                    }
                }
            } else {
                targetNode.delete(key);
            }
        }
    }

    public void handleMergeableChildrenInternal(
            BNode targetNode,
            BNodeKey precedingKey,
            BNodeKey succeedingKey,
            int key,
            BNode first,
            BNode second) {
        if (targetNode.getElementNum() <= this.order / 2) {
            int keyToReinsert;
            keyToReinsert = precedingKey != null ? precedingKey.getKey() : succeedingKey.getKey();
            delete(keyToReinsert);
            delete(key);
            insert(root, keyToReinsert);
        } else {
            targetNode.delete(key);
            BNode mergedChild = null;
            if(first != null && second != null)
                mergedChild = mergeBranches(first, second);
            setMergedChildOnLeftoverNodes(targetNode, key, mergedChild);
        }
    }

    public void setMergedChildOnLeftoverNodes(BNode targetNode, int key, BNode mergedChild) {
        BNodeKey nextInBlock = targetNode.getNextLargestKey(key);
        BNodeKey previousInBlock = targetNode.getNextSmallestKey(key);
        if (previousInBlock != null) previousInBlock.setRight(mergedChild);
        if (nextInBlock != null) nextInBlock.setLeft(mergedChild);
    }

    // finds an appropriate element to borrow from another place in the tree
    public int borrowAndDelete(BNode targetNode, int key) {
        BNode trackingNode = targetNode.getKey(key).getRight();
        int keyToBorrowFromRightSubtree = -1;
        int keyToBorrowFromLeftSubtree = -1;
        int sizeAtRight = 0;
        int sizeAtLeft = 0;
        if (trackingNode != null) {
            while (trackingNode.getFirst().getLeft() != null) {
                trackingNode = trackingNode.getFirst().getLeft();
            }

            keyToBorrowFromRightSubtree = trackingNode.getFirst().getKey();
            sizeAtRight = targetNode.getElementNum();
        }
        trackingNode = targetNode.getKey(key).getLeft();
        if (trackingNode != null) {
            while (trackingNode.getLast().getRight() != null) {
                trackingNode = trackingNode.getLast().getRight();
            }

            keyToBorrowFromLeftSubtree = trackingNode.getLast().getKey();
            sizeAtLeft = trackingNode.getElementNum();
        }
        return sizeAtLeft > sizeAtRight ? keyToBorrowFromLeftSubtree:keyToBorrowFromRightSubtree;
    }

    // We use this to borrow for the succeeding key the smallest item from the right sibling so that the target node has enough items to allow for deletion
    // We return true if this operation could actually be performed
    public boolean isLeafAndBorrowFromRightSibling(BNode parent, int key) {
        BNodeKey succeedingKey = parent.getNextLargestKey(key);
        boolean success = false;
        if (succeedingKey != null) {
            BNode rightSibling = succeedingKey.getRight();
            if (rightSibling.getElementNum() > this.order / 2) {
                borrowFromSucceedingKeyAndReplaceWithSmallestRightSibling(succeedingKey, rightSibling, key);
                success = true;
            }
        }
        return success;
    }

    public void borrowFromSucceedingKeyAndReplaceWithSmallestRightSibling(BNodeKey succeedingKey, BNode rightSibling, int key) {
        int itemToRemove = rightSibling.getFirst().getKey();
        int borrowItem = succeedingKey.getKey();
        rightSibling.delete(itemToRemove);
        succeedingKey.setKey(itemToRemove);
        succeedingKey.getLeft().addKey(borrowItem);
        succeedingKey.getLeft().delete(key);
    }

    public boolean isLeafAndBorrowFromLeftSibling(BNode parent, int key) {
        BNodeKey precedingKey = parent.getNextSmallestKey(key);
        boolean success = false;
        if (precedingKey != null) {
            BNode leftSibling = precedingKey.getLeft();
            if (leftSibling.getElementNum() > this.order / 2) {
                borrowFromPrecedingKeyAndReplaceWithLargestLeftSibling(precedingKey, leftSibling, key);
                success = true;
            }
        }
        return success;
    }

    public void borrowFromPrecedingKeyAndReplaceWithLargestLeftSibling(BNodeKey precedingKey, BNode leftSibling, int key) {
        int itemToRemove = leftSibling.getLast().getKey();
        int borrowItem = precedingKey.getKey();
        leftSibling.delete(itemToRemove);
        precedingKey.setKey(itemToRemove);
        precedingKey.getRight().addKey(borrowItem);
        precedingKey.getRight().delete(key);
    }

    //this function determines if the appropriate lower branches can be merged
    public boolean canMerge(BNode first, BNode second) {
        if(first == null && second == null) return true;
        while (first.getLast() != null && second.getFirst() != null) {
            if (first.getElementNum() + second.getElementNum() > order) {
                return false;
            }
            first = first.getLast().getLeft();
            second = second.getFirst().getRight();
            if(first == null || second == null) break;
        }
        return true;
    }

    //the function recursively merges the passed nodes and the relevant descendants
    public BNode mergeBranches(BNode first, BNode second) {
        if (first.isLeaf() && second.isLeaf()) {
            return first.mergeWithNode(second);
        } else {
            BNode mergedLowerNode = mergeBranches(first.getLast().getRight(), second.getFirst().getLeft());
            first.getLast().setRight(mergedLowerNode);
            second.getFirst().setLeft(mergedLowerNode);
            return first.mergeWithNode(second);
        }
    }


}
