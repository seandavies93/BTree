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
        System.out.print("State pre delete:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 93);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(29);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(67);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(101);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(140); // Now deletion does something weird after this point
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 200);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 150);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 161);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 167);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 1);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 2);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 3);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 4);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 201);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 300);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 35);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 78);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(150);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 501);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 502);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 503);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 504);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 505);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(501);
        System.out.print("State after 501:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(502);
        System.out.print("State after 502:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(503);
        System.out.print("State after 503:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(504);
        System.out.print("State after 504:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.delete(505); // FIXME: there seems to be a minor issues occurring here, but I think I might be close to ironing out slight inconsistencies in the ancillary code.
        System.out.println("State after 505:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        // Items left: 78Y, 35Y, 300Y, 4Y, 3Y, 2Y, 1Y, 167Y, 161Y, 200Y, 93Y, 50Y, 27Y, 28Y, 30Y, 56Y, 98Y
        // Inspect line 122 when deleting element 201 in deletion process


    }
}
