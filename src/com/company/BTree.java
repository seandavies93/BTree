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
        if (targetNode.isInternal()) {
            int keyToBorrow = borrowAndDelete(targetNode, key);
            BNodeKey oldKey = targetNode.getKey(key);
            if (canMerge(targetNode.getKey(key).getLeft(), targetNode.getKey(key).getRight())) {
                BNode first = oldKey.getLeft();
                BNode second = oldKey.getRight();
                BNodeKey precedingKey = targetNode.getNextLargestKey(key);
                BNodeKey succeedingKey = targetNode.getNextSmallestKey(key);
                if (targetNode != root && targetNode.getElementNum() <= this.order / 2) {
                    delete(keyToBorrow);
                    oldKey.setKey(keyToBorrow);
                } else {
                    targetNode.delete(key);
                    BNode mergedChild = mergeBranches(first, second);
                    if(targetNode == root && targetNode.getElementNum() == this.order / 2) {
                        root = mergedChild;
                        return;
                    }
                    if (precedingKey != null) precedingKey.setRight(mergedChild);
                    if (succeedingKey != null) succeedingKey.setLeft(mergedChild);
                }
            } else {
                delete(keyToBorrow);
                oldKey.setKey(keyToBorrow);
            }
        } else if (targetNode.isLeaf()) {
            BNodeKey precedingKey = parent.getNextSmallestKey(key);
            BNodeKey succeedingKey = parent.getNextLargestKey(key);
            if(targetNode.getElementNum() <= this.order / 2) {

                if(precedingKey != null) {
                    if (precedingKey.getLeft().getElementNum() + targetNode.getElementNum() <= this.order) {
                        int replacementKey = precedingKey.getKey();
                        delete(replacementKey);
                        targetNode.delete(key);
                        targetNode.addKey(replacementKey);
                    } else {
                        int parentKey = precedingKey.getKey();
                        int borrowKey = precedingKey.getLeft().getLast().getKey();
                        precedingKey.getLeft().delete(borrowKey);
                        precedingKey.setKey(borrowKey);
                        targetNode.getKey(key).setKey(parentKey);
                    }
                    return;
                }
                if (succeedingKey != null) {
                    if (succeedingKey.getRight().getElementNum() + targetNode.getElementNum() <= this.order) {
                        int replacementKey = succeedingKey.getKey();
                        delete(replacementKey);
                        succeedingKey.getRight().delete(key);
                        succeedingKey.getRight().addKey(replacementKey);
                    } else {
                        int parentKey = succeedingKey.getKey();
                        int borrowKey = succeedingKey.getRight().getFirst().getKey();
                        succeedingKey.getRight().delete(borrowKey);
                        succeedingKey.setKey(borrowKey);
                        targetNode.getKey(key).setKey(parentKey);
                    }
                }
            } else {
                targetNode.delete(key);
            }

        }
    }

    // finds an appropriate element to borrow from another place in the tree
    public int borrowAndDelete(BNode targetNode, int key) {
        BNode trackingNode = targetNode.getKey(key).getRight();
        int keyToBorrow = -1;
        while (trackingNode != null && !trackingNode.isLeaf()) {
            trackingNode = trackingNode.getFirst().getLeft();
        }
        if (trackingNode != null) {
            keyToBorrow = trackingNode.getFirst().getKey();
            if (keyToBorrow != -1)
                return keyToBorrow;
        }
        trackingNode = targetNode.getKey(key).getLeft();
        while (trackingNode != null && !trackingNode.isLeaf()) {
            trackingNode = trackingNode.getLast().getRight();
        }
        if (trackingNode != null) {
            keyToBorrow = trackingNode.getLast().getKey();
            if (keyToBorrow != -1)
                return keyToBorrow;
        }
        return keyToBorrow;
    }

    // can use this to determine if the merge technique can be used to delete before descending to the target node in the delete function
    public boolean canDeleteByMerge(BNode root, int key) {
        BNode targetNode = root;
        while (true) {
            if (targetNode.findAppropriateChild(key) == null) break;
            targetNode = targetNode.findAppropriateChild(key);
        }
        return canMerge(targetNode.getKey(key).getLeft(), targetNode.getKey(key).getRight());
    }

    // used to determine if we should borrow from above since the targetNode is a leaf node
    public boolean isTargetNodeALeaf(BNode root, int key) {
        BNode targetNode = root;
        while (true) {
            if (targetNode.findAppropriateChild(key) == null) break;
            targetNode = targetNode.findAppropriateChild(key);
        }
        return targetNode.isLeaf();
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
