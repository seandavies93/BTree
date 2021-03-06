package com.company;

public class BNodeKey {
    private BNode leftNode;
    private BNode rightNode;
    private int key;

    BNodeKey() {
        this.leftNode = null;
        this.rightNode = null;
        this.key = -1;
    }

    BNodeKey(BNode left, BNode right, int key) {
        this.leftNode = left;
        this.rightNode = right;
        this.key = key;
    }

    BNodeKey(BNode parent, BNodeKey splitFromBelow) {
        int median = parent.getMedian(splitFromBelow.getKey());
        BNode rightSplit = parent.splitNode(splitFromBelow);
        this.leftNode = parent;
        this.rightNode = rightSplit;
        this.key = median;
    }

    BNodeKey(BNode parent, int keyFromBelow) {
        this.leftNode = null;
        this.rightNode = null;
        this.key = keyFromBelow;
        int median = parent.getMedian(keyFromBelow);
        BNode rightSplit = parent.splitNode(this);
        this.leftNode = parent;
        this.rightNode = rightSplit;
        this.key = median;
    }

    public void setLeft(BNode left) {
        this.leftNode = left;
    }

    public void setRight(BNode right) {
        this.rightNode = right;
    }

    public BNode getLeft() {
        return this.leftNode;
    }

    public BNode getRight() {
        return this.rightNode;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean valueLessThanKeyAndWithinBounds(int keyValue) {
        return keyValue > key && key != -1;
    }

    public boolean notEmptyNodeKey() {
        return key != -1;
    }

    public boolean lessThan(int value) {
        return value > this.key;
    }

    public boolean greaterThan(int value) {
        return value < this.key;
    }

    public boolean equalTo(int value) {
        return value == this.key;
    }

    public void updateNodeKey(BNodeKey keyToUpdateWith) {
        setKey(keyToUpdateWith.getKey());
        setLeft(keyToUpdateWith.getLeft());
        setRight(keyToUpdateWith.getRight());
    }
}
