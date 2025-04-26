package org.example.lectures.three.group1;


import java.util.Set;

public class WeightedNode {

    Set<Edge> edges;
    boolean isFinal = false;
    static class Edge {
        WeightedNode node;
        int weight;

        public Edge(WeightedNode node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
