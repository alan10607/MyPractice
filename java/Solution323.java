package leetCode.java;

import java.util.Arrays;

//Union-Find O(ElogE) O(V), V = n, E = edges.length
class Solution323 {
    public int countComponents(int n, int[][] edges) {
        int[] parents = new int[n];
        Arrays.fill(parents, -1);
        int res = n; // 初始有n個components, 接合時-1
        for (int[] edge : edges) {
            int a = find(edge[0], parents);
            int b = find(edge[1], parents);
            if (a == b) { // 成環, 跳過
                continue;
            }
            parents[a] = b;
            --res; // 合併少一個components
        }

        return res;
    }
}