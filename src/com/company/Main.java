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

        treeTest.insert(treeTest.getRoot(), 3);
        treeTest.insert(treeTest.getRoot(), 4);
        treeTest.insert(treeTest.getRoot(), 5);
        treeTest.insert(treeTest.getRoot(), 6);
        treeTest.insert(treeTest.getRoot(), 7);
        treeTest.insert(treeTest.getRoot(), 8);
        treeTest.insert(treeTest.getRoot(), 26);
        treeTest.insert(treeTest.getRoot(), 10);
        treeTest.insert(treeTest.getRoot(), 11);
        treeTest.insert(treeTest.getRoot(), 12);
        treeTest.insert(treeTest.getRoot(), 25);
        treeTest.insert(treeTest.getRoot(), 14);
        treeTest.insert(treeTest.getRoot(), 15);
        treeTest.insert(treeTest.getRoot(), 16);
        treeTest.insert(treeTest.getRoot(), 17);
        treeTest.insert(treeTest.getRoot(), 18);
        treeTest.insert(treeTest.getRoot(), 19);
        treeTest.insert(treeTest.getRoot(), 20);
        treeTest.insert(treeTest.getRoot(), 21);
        treeTest.insert(treeTest.getRoot(), 22);
        treeTest.insert(treeTest.getRoot(), 23);
        treeTest.insert(treeTest.getRoot(), 24);
        treeTest.insert(treeTest.getRoot(), 110);
        treeTest.insert(treeTest.getRoot(), 109);
        treeTest.insert(treeTest.getRoot(), 104);
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
        treeTest.getRoot().getKey(140).getLeft().getKey(109).getLeft().printElements();

    }
}
