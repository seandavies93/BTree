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
}
