package leetCode.java;

import java.util.*;

//Union-Find O(ElogE) O(V), 本題V = E = edges.length
class Solution684 {
    public int[] findRedundantConnection(int[][] edges) {
        // If there are multiple answers, return the answer that occurs last in the input
        int n = edges.length;
        int[] parents = new int[n + 1]; // 題目給的val是在[1,n]
        Arrays.fill(parents, -1);
        for (int[] edge : edges) {
            int a = find(edge[0], parents);
            int b = find(edge[1], parents);
            if (a == b) {
                return edge;
            }
            parents[a] = b;
        }
        return new int[]{};
    }

    public int find(int node, int[] parents) {
        if (parents[node] == -1) {
            return node;
        }
        return parents[node] = find(parents[node], parents);
    }
}