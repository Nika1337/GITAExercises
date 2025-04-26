package org.example.lectures.four.group1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

record Edge1(int u, int v, int w) implements Comparable<Edge1> {
    @Override
    public int compareTo(Edge1 o) {
        return Integer.compare(this.w, o.w);
    }
}

record Result(int totalWeight, List<Edge1> path) {}

public class Kruskal {
    public static Result run(int n, List<Edge1> edges) throws Exception {
        Collections.sort(edges);

        DSU dsu = new DSU(n);

        int total = 0;
        List<Edge1> chosen = new ArrayList<>();

        for (Edge1 e : edges) {
            if (dsu.union(e.u(), e.v())) {
                chosen.add(e);
                total += e.w();
                if (chosen.size() == n - 1) {
                    break;
                }
            }
        }

        if (chosen.size() != n-1) {
            throw new Exception("n nodes not found");
        }



        return new Result(total, chosen);
    }
}
