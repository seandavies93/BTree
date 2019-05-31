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
                    int median = parent.getMedian(splitFromBelow.getKey());
                    BNode rightSplit = parent.splitNode(splitFromBelow);
                    BNodeKey toAdd = new BNodeKey(parent, rightSplit, median);
                    BNode newRoot = new BNode(order);
                    newRoot.addKeyFromSplit(toAdd);
                    root = newRoot;
                    return null;
                } else if (parent.isFull()) {
                    int median = parent.getMedian(splitFromBelow.getKey());
                    BNode rightSplit = parent.splitNode(splitFromBelow);
                    BNodeKey toReturn = new BNodeKey(parent, rightSplit, median);
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
                int median = parent.getMedian(key);
                toAdd.setKey(key);
                BNode rightSplit = parent.splitNode(toAdd);
                BNodeKey forRoot = new BNodeKey(parent, rightSplit, median);
                newRoot.addKeyFromSplit(forRoot);
                root = newRoot;
                return null;
            } else if (parent.isFull()) {
                int median = parent.getMedian(key);
                BNodeKey newKey = new BNodeKey();
                newKey.setKey(key);
                BNode rightSplit = parent.splitNode(newKey);
                BNodeKey toReturn = new BNodeKey(parent, rightSplit, median);
                return toReturn;
            } else {
                parent.addKey(key);
                return null;
            }
        }
    }

    public void delete(int key) {
        BNode parent =root;
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
            if(!canMerge(targetNode.getKey(key).getLeft(), targetNode.getKey(key).getRight())) {
                delete(keyToBorrow);
                oldKey.setKey(keyToBorrow);
            } else {
                boolean isSingleItem = (targetNode.getElementNum() == 1);
                targetNode.delete(key);
                BNode mergedChild = mergeBranches(first, second);
                if (precedingKey != null) precedingKey.setRight(mergedChild);
                if (succeedingKey != null) succeedingKey.setLeft(mergedChild);
                if (isSingleItem) root = mergedChild;
            }
        } else if (targetNode.isInternal()) {
            if (canMerge(targetNode.getKey(key).getLeft(), targetNode.getKey(key).getRight())) {
                if (targetNode.getElementNum() <= this.order / 2) {
                    delete(keyToBorrow);
                    oldKey.setKey(keyToBorrow);
                } else {
                    targetNode.delete(key);
                    BNode mergedChild = mergeBranches(first, second);
                    if (precedingKey != null) precedingKey.setRight(mergedChild);
                    if (succeedingKey != null) succeedingKey.setLeft(mergedChild);
                }
            } else {
                delete(keyToBorrow);
                oldKey.setKey(keyToBorrow);
            }
        } else if (targetNode.isLeaf()) {
            if (targetNode.getElementNum() <= this.order / 2) {
                if (!isLeafAndBorrowFromLeftSibling(parent, key)) isLeafAndBorrowFromRightSibling(parent, key);
            } else {
                targetNode.delete(key);
            }
        }

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
    // The following borrow functions overlook the case when both the left sibling and the right sibling have the same number of elements
    // I discovered an issue that prevents a deletion from occurring because this case falls through the cracks
    // Really though, in this case it seems like the merge functions should have flagged the case as mergable
    public boolean isLeafAndBorrowFromRightSibling(BNode parent, int key) {
        BNodeKey succeedingKey = parent.getNextLargestKey(key);
        boolean success = false;
        if (succeedingKey != null) {
            BNode rightSibling = succeedingKey.getRight();
            if (rightSibling.getElementNum() > this.order / 2) {
                int itemToRemove = rightSibling.getFirst().getKey();
                int borrowItem = succeedingKey.getKey();
                rightSibling.delete(itemToRemove);
                succeedingKey.setKey(itemToRemove);
                succeedingKey.getLeft().addKey(borrowItem);
                success = true;
            }
        }
        return success;
    }

    public boolean isLeafAndBorrowFromLeftSibling(BNode parent, int key) {
        BNodeKey precedingKey = parent.getNextSmallestKey(key);
        boolean success = false;
        if (precedingKey != null) {
            BNode leftSibling = precedingKey.getLeft();
            if (leftSibling.getElementNum() > this.order / 2) {
                int itemToRemove = leftSibling.getLast().getKey();
                int borrowItem = precedingKey.getKey();
                leftSibling.delete(itemToRemove);
                precedingKey.setKey(itemToRemove);
                precedingKey.getRight().addKey(borrowItem);
                success = true;
            }
        }
        return success;
    }

    //this function determines if the appropriate lower branches can be merged
    public boolean canMerge(BNode first, BNode second) {
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

    //the function recursively merges the passed nodes and the appropriate descendants
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
