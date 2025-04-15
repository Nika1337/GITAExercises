package org.example.lectures.two.group1;

public class Main {
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();

        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);


        tree.bfs();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        tree.inorder();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        tree.postorder();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        tree.preorder();
    }
}
