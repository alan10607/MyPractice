package leetCode.java;

import java.util.*;

//Union-Find O(ElogE) O(V), 本題V = E = edges.length
class Solution684 {
    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[edges.length + 1];//1~n但0不使用
        Arrays.fill(parents, -1);

        for(int[] edge : edges){
            int a = find(edge[0], parents);
            int b = find(edge[1], parents);

            if(a == b) return edge;

            parents[a] = b;
        }

        return new int[]{};
    }

    public int find(int node, int[] parents){
        if(parents[node] == -1) return node;
        return find(parents[node], parents);
    }
}