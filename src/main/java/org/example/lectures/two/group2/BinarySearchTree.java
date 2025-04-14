package org.example.lectures.two.group2;

public class BinarySearchTree {
    public Node root = null;

    public Node search(int value) {
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if (node == null || node.value == value) {
            return node;
        }

        if (value < node.value) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    public Node minimum() {
        return minimum(root);
    }

    private Node minimum(Node node) {
        if (node.left != null) {
            return minimum(node.left);
        }

        return node;
    }

    public Node maximum() {
        return maximum(root);
    }

    public Node maximum(Node node) {
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    public Node successor(Node node) {
        if (node.right != null) {
            return minimum(node.right);
        }

        Node parentNode = node.parent;

        while (parentNode != null && node == parentNode.right) {
            node = parentNode;
            parentNode = parentNode.parent;
        }

        return parentNode;
    }

    public Node predecessor(Node node) {
        if (node.left != null) {
            return maximum(node.left);
        }

        Node parentNode = node.parent;

        while (parentNode != null && node == parentNode.left) {
            node = parentNode;
            parentNode = parentNode.parent;
        }

        return parentNode;
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        Node parentNode = null;
        Node curr = root;

        while (curr != null) {
            parentNode = curr;

            if (value < curr.value) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        newNode.parent = parentNode;

        if (parentNode == null) {
            root = newNode;
        } else if (newNode.value < parentNode.value) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
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
                successor.parent = node.right;
                successor.right.parent = successor;
            }

            replace(node, successor);
            successor.left = node.left;
            successor.left.parent = successor;
        }
    }


    private void replace(Node nodeToBeReplaced, Node nodeToReplaceWith) {
        if (nodeToBeReplaced.parent == null) {
            root = nodeToReplaceWith;
        } else if (nodeToBeReplaced == nodeToBeReplaced.parent.left) {
            nodeToBeReplaced.parent.left = nodeToReplaceWith;
        } else if (nodeToBeReplaced == nodeToReplaceWith.parent.right) {
            nodeToBeReplaced.parent.right = nodeToReplaceWith;
        }

        if (nodeToReplaceWith != null) {
            nodeToReplaceWith.parent = nodeToBeReplaced.parent;
        }
    }
}












