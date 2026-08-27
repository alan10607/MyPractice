package leetCode.java;

import java.util.*;

//Bipartition DFS O(V + E) O(V)
class Solution785 {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // 0:unvisited, 1/-1:代表兩色
        for (int i = 0; i < n; ++i) {
            if (colors[i] == 0 && !dfs(graph, i, 1, colors)) { // 為什麼都設成1, 不同的dfs不會衝突
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int[][] graph, int i, int color, int[] colors) {
        if (colors[i] != 0) { // 若已經染色, 則只判斷到這個點
            return colors[i] == color;
        }

        colors[i] = color; // 染色

        for (int next : graph[i]) {
            if (!dfs(graph, next, color * -1, colors)) { // 繼續確認下一個, 並染不同色
                return false;
            }
        }
        return true;
    }
}