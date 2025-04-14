package org.example.lectures.two.group2;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    Node root = null;

    public void insert(int value) {
        Node node = new Node(value);

        if (root == null) {
            root = node;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.left == null) {
                curr.left = node;
                node.parent = curr;
                return;
            } else {
                queue.add(curr.left);
            }

            if (curr.right == null) {
                curr.right = node;
                node.parent = curr;
                return;
            } else {
                queue.add(curr.right);
            }
        }
    }


    // left, root, right
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.value);
            inorder(node.right);
        }
    }

    // left, right, node
    public void postorder() {
        postorder(root);
    }
    private void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.value);
        }
    }


    // root, left, right
    public void preorder() {
        preorder(root);
    }

    private void preorder(Node node) {
        if (node != null) {
            System.out.println(node.value);
            preorder(node.left);
            preorder(node.right);
        }
    }

    // depth first search
    // breadth first search
    private void bfs() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.println(curr.value);

            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
    }

}
