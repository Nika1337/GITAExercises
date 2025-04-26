package org.example.lectures.four.group1;

import org.example.lectures.four.Solution.WeightedNode;

import java.util.*;


public class DijkstaMaze {
    public List<WeightedNode> getShortestPathToEnd(WeightedNode start) {
        record Pair(WeightedNode node, int dist) {}
        Map<WeightedNode, Integer> dist = new HashMap<>();
        Map<WeightedNode, WeightedNode> prev = new HashMap<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));
        dist.put(start, 0);

        WeightedNode endNode = null;

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            if (curr.dist() != dist.get(curr.node)) continue;

            if (curr.node.isFinal) {
                endNode = curr.node;
                break;
            }

            for (WeightedNode.Edge e : curr.node.outgoingEdges) {
                int alt = e.weight + curr.dist;

                if (alt < dist.getOrDefault(e.destination, Integer.MAX_VALUE)) {
                    dist.put(e.destination, alt);
                    prev.put(e.destination, curr.node);
                    pq.add(new Pair(e.destination, alt));
                }
            }
        }

        if (endNode == null) {
            return List.of();
        }

        LinkedList<WeightedNode> path = new LinkedList<>();

        for (WeightedNode node = endNode; node != null; node = prev.get(node)) {
            path.addFirst(node);
        }

        return path;
    }

    public static void main(String[] args) {
        WeightedNode start = new WeightedNode();

        WeightedNode a = new WeightedNode();

        WeightedNode a1 = new WeightedNode();
        WeightedNode a2 = new WeightedNode();
        a2.isFinal = true;
        WeightedNode a3 = new WeightedNode();

        a.outgoingEdges.add(new WeightedNode.Edge(a1, 1));
        a.outgoingEdges.add(new WeightedNode.Edge(a2, 1));
        a.outgoingEdges.add(new WeightedNode.Edge(a3, 1));

        WeightedNode b = new WeightedNode();


        WeightedNode c = new WeightedNode();


        WeightedNode d = new WeightedNode();

        start.outgoingEdges.add(new WeightedNode.Edge(a, 1));
        start.outgoingEdges.add(new WeightedNode.Edge(b, 1));
        start.outgoingEdges.add(new WeightedNode.Edge(c, 1));
        start.outgoingEdges.add(new WeightedNode.Edge(d, 1));

        List<WeightedNode> shortestPath = new ArrayList<>();
        shortestPath.add(start);
        shortestPath.add(a);
        shortestPath.add(a2);
    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        record Edge(int node, double succProb) {}
        ArrayList<LinkedList<Edge>> neighboursArr = new ArrayList<>(Collections.nCopies(n, new LinkedList<>()));

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            neighboursArr.get(edge[0]).add(new Edge(edge[1], succProb[i]));
        }

        double[] dist = new double[n];

        record Pair(double succProb, int node) {}
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        pq.add(new Pair(1, start_node));
        dist[start_node] = 1;

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            if (curr.succProb() != dist[curr.node()]) continue;

            if (curr.node() == end_node) {
                return curr.succProb();
            }

            for (Edge neighbour : neighboursArr.get(curr.node())) {
                double alternate = curr.succProb() * neighbour.succProb();

                if (alternate > dist[neighbour.node()]) {
                    dist[neighbour.node()] = alternate;
                    pq.add(new Pair(alternate, neighbour.node()));
                }
            }
        }

        return 0;
    }
}
