package com.company;

public class Main {

    public static void main(String[] args) {
        BTree treeTest = new BTree(4);

        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 101);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 98);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 67);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 56);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 30);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 29);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 28);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 27);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 140);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 50);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 93);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(93);
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(98);
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(101);
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(140);
        treeTest.printTree(treeTest.getRoot(), 0);
    }
}
