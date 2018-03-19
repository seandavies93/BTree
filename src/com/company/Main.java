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
		System.out.println("First Splitting:\n");
		treeTest.printTree(treeTest.getRoot(), 0);
	    treeTest.insert(treeTest.getRoot(), 8);
	    treeTest.insert(treeTest.getRoot(), 26);
	    treeTest.insert(treeTest.getRoot(), 10);
	    treeTest.insert(treeTest.getRoot(), 11);
		System.out.println("Second Splitting:\n");
		treeTest.printTree(treeTest.getRoot(), 0);
	    treeTest.insert(treeTest.getRoot(), 12);
	    treeTest.insert(treeTest.getRoot(), 25);
	    treeTest.insert(treeTest.getRoot(), 14);
	    treeTest.insert(treeTest.getRoot(), 15);
		System.out.println("Third Splitting:\n");
		treeTest.printTree(treeTest.getRoot(), 0);
	    treeTest.insert(treeTest.getRoot(), 16);
	    treeTest.insert(treeTest.getRoot(), 17);
	    treeTest.insert(treeTest.getRoot(), 18);
	    treeTest.insert(treeTest.getRoot(), 19);
		System.out.println("Fourth Splitting:\n");
		treeTest.printTree(treeTest.getRoot(), 0);
	    treeTest.insert(treeTest.getRoot(), 20);
	    treeTest.insert(treeTest.getRoot(), 21);
	    treeTest.insert(treeTest.getRoot(), 22);
	    treeTest.insert(treeTest.getRoot(), 23);
		System.out.println("Fifth Splitting:\n");
		treeTest.printTree(treeTest.getRoot(), 0);
	    treeTest.insert(treeTest.getRoot(), 24);




    }
}
