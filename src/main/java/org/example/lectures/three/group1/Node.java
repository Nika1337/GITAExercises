package org.example.lectures.three.group1;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public List<Node> neighbours = new ArrayList<>();
    public boolean isFinal = false;
    public int weight = -1;
}
