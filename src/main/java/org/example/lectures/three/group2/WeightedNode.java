package org.example.lectures.three.group2;

import java.util.HashSet;
import java.util.Set;

public class WeightedNode {
    Set<Edge> outgoingEdges = new HashSet<>();
    public boolean isFinal = false;

    static class Edge {
        WeightedNode destination;
        int weight;

        public Edge(WeightedNode destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Edge other)) return false;

            return destination.equals(other.destination);
        }

        @Override
        public int hashCode() {
            return destination.hashCode();
        }
    }
}
