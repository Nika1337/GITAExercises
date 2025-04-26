package org.example.lectures.four.group1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

record Edge(int to, int weight) {}
public class Dijkstra {
    List<List<Edge>> neighbours;

    public Dijkstra(int n) {
        neighbours = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            neighbours.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int w) {
        neighbours.get(u).add(new Edge(v, w));
    }

    public int[] run(int src) {
        int n = neighbours.size();

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        record Pair(int weight, int v) {}
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, src));


        while (!pq.isEmpty()) {
            Pair curr = pq.poll();

            if (curr.weight() != dist[curr.v()]) continue;

            for (Edge e : neighbours.get(curr.v())) {
                int alternativeDistance = curr.weight() + e.weight();

                if (alternativeDistance < dist[e.to()]) {
                    dist[e.to()] = alternativeDistance;
                    pq.add(new Pair(alternativeDistance, e.to()));
                }
            }
        }

        return dist;
    }
 }
