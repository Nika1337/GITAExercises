package org.example.lectures.three.group2;

import java.util.HashSet;
import java.util.Set;

public class Node {
    public Set<Node> neighbours = new HashSet<>();
    public boolean isFinal = false;
}
