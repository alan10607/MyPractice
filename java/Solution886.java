package leetCode.java;

import java.util.*;

//Bipartition DFS O(V + E) O(V)
class Solution886 {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int[] dislike : dislikes) {
            edges.putIfAbsent(dislike[0], new ArrayList<>());
            edges.get(dislike[0]).add(dislike[1]);
            edges.putIfAbsent(dislike[1], new ArrayList<>());
            edges.get(dislike[1]).add(dislike[0]);
        }

        int[] colors = new int[n + 1]; // 0:未分組, 1/-1:兩組, 題目設定是[1, n], colors[0]不使用
        for (int i = 1; i < n + 1; ++i) {
            if (colors[i] == 0 && !dfs(i, 1, colors, edges)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int i, int color, int[] colors, Map<Integer, List<Integer>> edges) {
        if (colors[i] != 0) {
            return colors[i] == color;
        }

        colors[i] = color;

        if (edges.containsKey(i)) {
            for (int next : edges.get(i)) {
                if (!dfs(next, color * -1, colors, edges)) {
                    return false;
                }
            }
        }
        return true;
    }
}