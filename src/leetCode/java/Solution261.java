package leetCode.java;

import java.util.*;

//Union-Find O(VlogE) O(V), V = n, E = edges.length, logE因為每次探索最多可能跑n-1次
class Solution261 {//lintcode178
    public boolean validTree(int n, int[][] edges) {
        //1 V = E + 1
        if(n != edges.length + 1) return false;

        //2 成環就不為tree
        int[] parents = new int[n];
        Arrays.fill(parents, -1);
        for(int[] edge : edges){
            int a = find(edge[0], parents);
            int b = find(edge[1], parents);

            if(a == b) return false;//成環
            parents[a] = b;
        }
        return true;
    }

    public int find(int node, int[] parents){
        if(parents[node] == -1) return node;
        return find(parents[node], parents);
    }
}
/* 判斷無向圖Undirected graph是否為tree
1. V = E + 1
2. 查看是否有成環
ex. n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
0--1--2--3
   |-----|
   4
*/