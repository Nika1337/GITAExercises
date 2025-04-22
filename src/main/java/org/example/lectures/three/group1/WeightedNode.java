package org.example.lectures.three.group1;


import java.util.List;

public class WeightedNode {

    List<Edge> edges;
    static class Edge {
        WeightedNode node;
        int weight;

        public Edge(WeightedNode node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}
