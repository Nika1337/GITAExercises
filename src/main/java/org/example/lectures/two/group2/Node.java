package org.example.lectures.two.group2;

public class Node {
    public int value;
    public Node left;
    public Node right;
    public Node parent;

    public Node(int value) {
        this.value = value;
        left = right = parent = null;
    }
}
