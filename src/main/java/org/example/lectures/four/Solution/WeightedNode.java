package org.example.lectures.four.Solution;

import java.util.HashSet;
import java.util.Set;

public class WeightedNode {
    public Set<WeightedNode.Edge> outgoingEdges = new HashSet<>();
    public boolean isFinal = false;

    public static class Edge {
        public WeightedNode destination;
        public int weight;

        public Edge(WeightedNode destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof WeightedNode.Edge other)) return false;

            return destination.equals(other.destination);
        }

        @Override
        public int hashCode() {
            return destination.hashCode();
        }
    }
}