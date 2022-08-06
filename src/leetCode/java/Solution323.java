package leetCode.java;

import java.util.Arrays;

//Union-Find O(VlogE) O(V), V = n, E = edges.length
class Solution323 {
    public int countComponents(int n, int[][] edges) {
        int[] parents = new int[n];
        Arrays.fill(parents, -1);
        for(int[] edge : edges){
            int a = find(edge[0], parents);
            int b = find(edge[1], parents);

            if(a != b){
                n--;
                parents[a] = b;
            }
        }
        return n;
    }

    public int find(int node, int[] parents){
        if(parents[node] == -1) return node;
        return find(parents[node], parents);
    }
}