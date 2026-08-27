package leetCode.java;

import java.util.*;

//Shortest Path Dijkstra Algorithm O(n^2 * logn) O(n^2)
class Solution778 {
    public int swimInWater(int[][] grid) {
        // 這題是求最短路徑, Dijkstra Algorithm, 把高度想成距離就可以, 高度(距離)最低的優先走
        int n = grid.length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // <[i, j, 高度]>, 依照高度小到大
        pq.offer(new int[]{0, 0, grid[0][0]});
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            grid[node[0]][node[1]] = -1; // visited
            if (node[0] == n - 1 && node[1] == n - 1) {
                return node[2];
            }

            for (int[] dir : dirs) {
                int i = node[0] + dir[0];
                int j = node[1] + dir[1];
                if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == -1) {
                    continue;
                }
                pq.offer(new int[]{i, j, Math.max(node[2], grid[i][j])}); // Dijkstra思路的話, 要在這裡比較放入pq
            }
        }
        return -1;
    }
}


//BFS O(n^2 * logn) O(n^2)
class Solution778_2 {
    public int swimInWater(int[][] grid) {
        // 用BFS思路也可以, 差別只在於這個版本是先放入pq再拿出來比較
        int res = 0, n = grid.length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // <[i, j, 高度]>, 依照高度小到大
        pq.offer(new int[]{0, 0, grid[0][0]});
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            grid[node[0]][node[1]] = -1; // visited
            res = Math.max(res, node[2]);
            if (node[0] == n - 1 && node[1] == n - 1) {
                return res;
            }

            for (int[] dir : dirs) {
                int i = node[0] + dir[0];
                int j = node[1] + dir[1];
                if (i < 0 || i >= n || j < 0 || j >= n || grid[i][j] == -1) {
                    continue;
                }
                pq.offer(new int[]{i, j, grid[i][j]});
            }
        }
        return -1;
    }
}
/*
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

BFS遍歷順序優先找小的:

				0
		24				1
					23		2
						 22    3
						     21  4
							    ...
*/