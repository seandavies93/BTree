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
        treeTest.delete(505);
        System.out.println("State after 505:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 400);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 401);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 402);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 403);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 404);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 450);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 350);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 351);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 460);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 470);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 330); // TODO: insertion at this point is causing problems
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 331);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 332);
        System.out.print("State:\n");
        treeTest.printTree(treeTest.getRoot(), 0);
        treeTest.insert(treeTest.getRoot(), 333);
        // Items left: 78Y, 35Y, 300Y, 4Y, 3Y, 2Y, 1Y, 167Y, 161Y, 200Y, 201Y, 93Y, 50Y, 27Y,
        // 28Y, 30Y, 56Y, 98Y, 400, 401, 402, 403, 404, 450, 350, 351, 460, 470, 330, 331, 332, 333

        /*
        0
0
 0: 50 1: 350 2: -1 3: -1
1
 0: 3 1: 28 2: -1 3: -1
2
 0: 1 1: 2 2: -1 3: -1
2
 0: 4 1: 27 2: -1 3: -1
2
 0: 30 1: 35 2: -1 3: -1
1
 0: 161 1: 201 2: -1 3: -1
2
 0: 56 1: 78 2: 93 3: 98
2
 0: 167 1: 200 2: -1 3: -1
2
 0: 300 1: 330 2: 331 3: 332
1
 0: 401 1: 404 2: -1 3: -1
2
 0: 351 1: 400 2: -1 3: -1
2
 0: 402 1: 403 2: -1 3: -1
2
 0: 450 1: 460 2: 470 3: -1
         */

    }
}
