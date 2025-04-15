package org.example.lectures.two.group1;

public class BinarySearchTree extends BinaryTree {
    Node root;

    public Node search(int value) {
        return search(root, value);
    }


    public Node search(Node node, int value) {
        if (node == null) {
            return null;
        } else if (node.value == value) {
            return node;
        }


        if (value > node.value) {
            return search(node.right, value);
        } else {
            return search(node.left, value);
        }
    }

    public int minimum() {
        return minimum(root).value;
    }

    private Node minimum(Node root) {
        Node node = root;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }


    public int maximum() {
        return maximum(root).value;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    private Node successor(Node node) {
        if (node.right != null) {
            return minimum(node.right);
        }

        Node curr = node;
        Node parent = curr.parent;

        while (parent != null && curr == parent.right) {
            curr = parent;
            parent = parent.parent;
        }

        return parent;
    }

    private Node predecessor(Node node) {
        if (node.left != null) {
            return maximum(node.left);
        }

        Node curr = node;
        Node parent = curr.parent;

        while (parent != null && curr == parent.left) {
            curr = parent;
            parent = parent.parent;
        }

        return parent;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;
            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = parent;

        if (parent == null) {
            root = newNode;
        } else if (newNode.value < parent.value) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    private void replace(Node u, Node v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    public void delete(Node node) {
        if (node.left == null) {
            replace(node, node.right);
        } else if (node.right == null) {
            replace(node, node.left);
        } else {
            Node successor = successor(node);

            if (successor.parent != node) {
                replace(successor, successor.right);
                successor.right = node.right;
                node.right.parent = successor;
            }

            replace(node, successor);
            successor.left = node.left;
            successor.left.parent = successor;
        }
    }
 }
