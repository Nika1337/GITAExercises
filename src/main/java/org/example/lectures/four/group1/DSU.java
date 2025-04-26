package org.example.lectures.four.group1;

import java.util.Arrays;

public class DSU {

    private final int[] parents;

    public DSU(int n) {
        parents = new int[n];
        Arrays.fill(parents, -1);
    }

    public int find(int n) {
        if (parents[n] < 0) return n;
        parents[n] = find(parents[n]);
        return parents[n];
    }

    public boolean union(int u, int v) {
        int up = find(u);
        int vp = find(v);

        if (up == vp) return false;

        // up = -10 vp = -11, vp has bigger absolute value, hence its bigger
        if (up > vp) {
            int temp = up;
            up = vp;
            vp = temp;
        }

        parents[up] += parents[vp];
        parents[vp] = up;


        return true;
    }

}
