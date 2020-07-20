package com.company;

public class BNode {
    private int order;
    private int elementNum;
    private BNodeKey[] elements;

    //constructor
    BNode(int order) {
        elements = new BNodeKey[order];
        elementNum = 0;
        for (int i = 0; i < order; i++) {
            elements[i] = new BNodeKey();
        }
        this.order = order;
    }

    //inner method for adding a key into the internal array, without regard for the BTree constraints
    public void addKey(int key) {
        int insertIndex = 0;
        if (isEmpty()) {
            elements[0].setKey(key);
            elementNum++;
            return;
        }

        while (elements[insertIndex].valueLessThanKeyAndWithinBounds(key)) {
            insertIndex++;
        }

        if (elements[insertIndex].notEmptyNodeKey()) {
            for (int i = order - 1; i >= insertIndex + 1; i--) {
                elements[i].updateNodeKey(elements[i - 1]);
            }
        }
        elements[insertIndex].setKey(key);
        elementNum++;
    }

    public void addKeyFull(BNodeKey key) {
        int insertIndex = 0;
        if (key == null) return;
        int value = key.getKey();
        if (isEmpty()) {
            elements[0] = key;
            elementNum++;
            return;
        }

        while (elements[insertIndex].valueLessThanKeyAndWithinBounds(value)) {
            insertIndex++;
        }
        if (elements[insertIndex].getKey() != -1) {
            for (int i = order - 1; i >= insertIndex + 1; i--) {
                elements[i].updateNodeKey(elements[i - 1]);
            }
        }
        elements[insertIndex] = key;
        elementNum++;
    }

    public void printElements() {
        for (int i = 0; i < order; i++) {
            System.out.print(" " + i + ": ");
            System.out.print(elements[i].getKey());
        }
    }

    //gets the current number of elements inserted into the predetermined array size
    public int getElementNum() {
        return elementNum;
    }

    public BNodeKey getElementByIndex(int index) {
        return elements[index];
    }

    //finds the appropriate child node to move to given the value
    public BNode findAppropriateChild(int key) {
        int index = 0;
        if (isEmpty()) return null;
        if (elements[0].getKey() > key) {
            return elements[0].getLeft();
        } else if (elements[elementNum - 1].getKey() < key) {
            return elements[elementNum - 1].getRight();
        }
        for (int i = 0; i < elementNum; i++) {
            if (elements[i].getKey() < key && key < elements[i + 1].getKey()) {
                return elements[i].getRight();
            }
        }
        return null;
    }

    //used to determine if the node has the specified key
    public boolean hasKey(int key) {
        int first = 0;
        int last = order - 1;
        int middle = (first + last) / 2;
        while (last > first) {
            if (key < elements[middle].getKey()) {
                last = middle - 1;
                middle = (first + last) / 2;
            } else if (key > elements[middle].getKey()) {
                first = middle + 1;
                middle = (first + last) / 2;
            } else if (key == elements[middle].getKey()) {
                return true;
            }
        }
        return false;
    }

    public int getMedian(int key) {
        int[] keys = new int[order + 1];
        int i = 0;
        while (elements[i].getKey() < key && i < order) {
            keys[i] = elements[i].getKey();
            i++;
            if(i >= order) break;
        }
        keys[i] = key;
        i++;
        while (i < order + 1) {
            keys[i] = elements[i - 1].getKey();
            i++;
        }
        return keys[(order + 1) / 2];
    }

    public BNode splitNode(BNodeKey key) {
        // TODO: check for an orphaning of a node here
        BNode newNode = new BNode(order);
        int index = 0;
        int initialNumElements = elementNum;
        int median = getMedian(key.getKey());
        while (elements[index].getKey() < median && index < order) {
            index++;
            if (index == order) break;
        }
        for (int i = index; i < initialNumElements; i++) {
            if (elements[i].getKey() == median) {
                elements[i] = new BNodeKey();
                this.elementNum--;
            } else {
                newNode.addKeyFull(elements[i]);
                elements[i] = new BNodeKey();
                this.elementNum--;
            }

        }
        // The exception here is when the median happens to be the same as the key we are inserting
        // Then we do not want to both insert it at the current level and propagate it up the tree after a split
        if(median != key.getKey()) {
            if (this.getElementNum() < this.order / 2) {
                if (!this.isLeaf()) this.addKeyFromSplit(key);
                else this.addKey(key.getKey());
            } else {
                if (!newNode.isLeaf()) newNode.addKeyFromSplit(key);
                else newNode.addKey(key.getKey());
            }
        } else {
            // TODO: possible current fix (after a test I would tentatively say that this was what was required)
            // this is for the case the the median is that same for two rounds of recursion in insert
            // we need to manually move over the right node otherwise it gets orphaned in this particular case
            newNode.getKeyAtIndex(0).setLeft(key.getRight());
        }
        return newNode;
    }

    public void addKeyFromSplit(BNodeKey key) {
        int insertIndex = 0;
        int value = key.getKey();
        if (isEmpty()) {
            elements[0] = key;
            elementNum++;
            return;
        }

        while (value > elements[insertIndex].getKey() && elements[insertIndex].getKey() != -1) {
            insertIndex++;
        }
        if (elements[insertIndex].getKey() != -1) {
            for (int i = order - 1; i >= insertIndex + 1; i--) {
                elements[i].updateNodeKey(elements[i - 1]);
            }
        }
        elements[insertIndex] = key;
        elementNum++;
        BNodeKey nextSmallest = this.getNextSmallestKey(value);
        if (nextSmallest != null) nextSmallest.setRight(key.getLeft());
        BNodeKey nextLargest = this.getNextLargestKey(value);
        if (nextLargest != null) nextLargest.setLeft(key.getRight());
    }

    public void setElementNum(int elementNum) {
        this.elementNum = elementNum;
    }

    public void delete(int key) {
        int deleteIndex = this.getIndexOfKey(key);
        for (int i = deleteIndex; i < order - 1; i++) {
            elements[i].updateNodeKey(elements[i + 1]);
        }
        elementNum--;
        for (int i = elementNum; i < order; i++) {
            elements[i].updateNodeKey(new BNodeKey());
        }
    }

    //returns first and last elements
    public BNodeKey getFirst() {
        if (elementNum != 0) {
            return elements[0];
        }
        return null;
    }

    public BNodeKey getLast() {
        if (elementNum != 0) {
            return elements[elementNum - 1];
        }
        return null;
    }

    public BNode mergeWithNode(BNode otherNode) {
        // We don't need to update anything in the current node as it will be garbage collected
        int endIndex = otherNode.getElementNum();
        for (int i = 0; i < endIndex; i++) {
            this.addKeyFull(otherNode.getKeyAtIndex(i));
        }
        return this;
    }

    public BNodeKey getNextLargestKey(int key) {
        int i = 0;
        while (key >= elements[i].getKey() && elements[i].getKey() != -1) {
            if (i == elementNum - 1) return null;
            i++;
        }
        return elements[i];
    }

    public BNodeKey getNextSmallestKey(int key) {
        int i = 0;
        while (key > elements[i].getKey() && elements[i].getKey() != -1) {
            if (i == order - 1) {
                return elements[i];
            }
            i++;
        }
        if (i == 0) return null;
        return elements[i - 1];
    }

    public int getIndexOfKey(int key) {
        int first = 0;
        int last = elementNum - 1;
        int middle = (first + last) / 2;
        while (last >= first) {
            if (key < elements[middle].getKey()) {
                last = middle - 1;
                middle = (first + last) / 2;
            } else if (key > elements[middle].getKey()) {
                first = middle + 1;
                middle = (first + last) / 2;
            } else if (key == elements[middle].getKey()) {
                return middle;
            }
        }
        return -1;
    }

    public BNodeKey getKey(int key) {
        int i = 0;
        while (i < order) {
            if (key == elements[i].getKey()) {
                return elements[i];
            }
            i++;
        }
        return null;
    }

    public BNodeKey getKeyAtIndex(int index) {
        return elements[index];
    }

    public boolean isLeaf() {
        for (int i = 0; i < order; i++) {
            if (elements[i].getLeft() != null && elements[i].getRight() != null) {
                return false;
            }
        }
        return true;
    }

    public boolean isInternal() {
        for (int i = 0; i < order; i++) {
            if (elements[i].getRight() != null || elements[i].getLeft() != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        return elementNum == order;
    }

    public boolean isEmpty() {
        return elementNum == 0;
    }
}
