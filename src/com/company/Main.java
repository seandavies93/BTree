package com.company;

public class Main {

    public static void main(String[] args) {
        BTree treeTest = new BTree(4);
	    /*
	    treeTest.getRoot().addKey(3);
	    treeTest.getRoot().addKey(4);
	    treeTest.getRoot().addKey(5);
	    treeTest.getRoot().addKey(6);
	    */

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

    }
}
