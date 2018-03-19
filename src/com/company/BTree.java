package com.company;

public class BTree {
    private BNode root;
    private int order;

    BTree(int order)
    {
        this.order = order;
        this.root = new BNode(this.order);
    }
    public BNode getRoot()
    {
        return this.root;
    }
    public BNode search(int key) {
        BNode currentNode = root;
        while (currentNode.findAppropriateChild(key) != null) {
            currentNode = currentNode.findAppropriateChild(key);
        }
        return currentNode;
    }
    public void printTree(BNode root, int level)
    {
        if(root == null) return;
        System.out.println(level);
        root.printElements();
        System.out.print("\n");
        level += 1;
        if(root.isEmpty())
        {
            if(root.getEmptySplitLink() != null)
            {
                printTree(root.getEmptySplitLink(), level);
            }

        }
        else
        {
            for(int i=0; i<root.getElementNum(); i++)
            {
                if(root.getElementByIndex(i).getLeft() != null)
                {
                    printTree(root.getElementByIndex(i).getLeft(), level);
                }

            }
            if(root.getLast().getRight() != null)
            {
                printTree(root.getLast().getRight(), level);
            }

        }
    }
    public BNode insert(BNode parent, int key) {
        BNode nextNode = parent.findAppropriateChild(key);
        if (nextNode != null) {
            BNode childSplitNode = insert(nextNode, key);
            if (childSplitNode != null) {
                if ((parent == root) && (parent.isFull())) {
                    BNode newRoot = new BNode(order);
                    newRoot.addKey(key);
                    BNode link = new BNode(order);
                    BNode newNode = parent.splitNode(key, link, childSplitNode);
                    newRoot.getKey(key).setRight(newNode);
                    newRoot.getKey(key).setLeft(parent);
                    root = newRoot;
                    return null;
                } else if (parent.isFull()) {
                    BNode link = parent.findAppropriateChild(key);
                    BNode rightHalf = parent.splitNode(key, link, childSplitNode);
                    return rightHalf;
                } else {
                    parent.addKey(key);
                    parent.setEmptySplitLink(null);
                    parent.getKey(key).setRight(childSplitNode);
                    BNodeKey nextSmallest = parent.getNextSmallestKey(key);
                    if(nextSmallest != null) parent.getKey(key).setLeft(nextSmallest.getRight());
                    BNodeKey nextLargest = parent.getNextLargestKey(key);
                    if(nextLargest != null) nextLargest.setLeft(childSplitNode);
                    return null;
                }
            } else {
                return null;
            }
        } else {
            if ((parent == root) && (parent.isFull())) {
                BNode newRoot = new BNode(order);
                newRoot.addKey(key);
                BNode newNode = parent.splitNode(key, null, null);
                //manually handle the add cases since the newRoot will always have only one element
                newRoot.getKey(key).setRight(newNode);
                newRoot.getKey(key).setLeft(parent);
                root = newRoot;
                return null;
            } else if (parent.isFull()) {
                BNode rightHalf = parent.splitNode(key, null, null);
                return rightHalf;
            } else {
                parent.addKey(key);
                return null;
            }
        }
    }

    public void delete(int key) {
        BNode parent = root;
        BNode targetNode = root;
        while (true) {
            parent = targetNode;
            if(targetNode.findAppropriateChild(key) == null) break;
            targetNode = targetNode.findAppropriateChild(key);

        }
        if (targetNode.isInternal()) {
            if (targetNode.getElementNum() == 1) {
                BNodeKey keyToDelete = targetNode.getKey(key);
                BNode borrowNode = keyToDelete.getRight();
                BNodeKey borrowedItem;
                if (borrowNode != null) {
                    borrowedItem = borrowNode.getFirst();
                } else {
                    borrowNode = keyToDelete.getLeft();
                    borrowedItem = borrowNode.getLast();
                }
                delete(borrowedItem.getKey());
                targetNode.delete(key);
                targetNode.addKey(borrowedItem.getKey());
            } else {
                if (canMerge(targetNode.getKey(key).getLeft(), targetNode.getKey(key).getRight())) {
                    BNodeKey elementToDelete = targetNode.getKey(key);
                    BNode first = elementToDelete.getLeft();
                    BNode second = elementToDelete.getRight();
                    targetNode.delete(key);
                    targetNode.setChildNode(mergeBranches(first, second));
                } else {
                    BNodeKey keyToDelete = targetNode.getKey(key);
                    BNode borrowNode = keyToDelete.getRight();
                    int keyToBorrow;
                    if (borrowNode != null) {
                        keyToBorrow = borrowNode.getFirst().getKey();
                    } else {
                        borrowNode = keyToDelete.getLeft();
                        keyToBorrow = borrowNode.getLast().getKey();
                    }
                    delete(keyToBorrow);
                    targetNode.delete(key);
                    targetNode.addKey(keyToBorrow);
                }
            }
        } else if (targetNode.isLeaf()) {
            if (targetNode.getElementNum() == 1) {
                BNodeKey borrowItem = parent.getNextLargestKey(key);
                delete(borrowItem.getKey());
                targetNode.delete(key);
                targetNode.addKey(borrowItem.getKey());
            } else {
                targetNode.delete(key);
            }
        }
    }

    //this function determines if the appropriate lower branches can be merged
    public boolean canMerge(BNode first, BNode second) {
        while(true) {
            if (first.getElementNum() + second.getElementNum() > order) {
                return false;
            }
            first = first.getLast().getLeft();
            second = second.getFirst().getRight();
            if(second == null || first == null) break;
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
