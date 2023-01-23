package leetCode.java;

import java.util.*;

//BFS Shortest Path Dijkstra Algorithm O(n^2 * logn) O(n^2)
class Solution778 {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int res = -1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);//<[x, y, 水面高度], ...>
        pq.offer(new int[]{0, 0, grid[0][0]});//起點

        while(!pq.isEmpty()){
            int[] block = pq.poll();
            int x = block[0];
            int y = block[1];
            int height = block[2];
            if(visited[x][y]) continue;//在這裡才判斷跳出, 留給heap判斷是否visited

            visited[x][y] = true;
            res = Math.max(res, height);//更新高度
            if(x == n - 1 && y == n - 1) break;//到達終點

            for(int[] dir : dirs){
                int i = x + dir[0];
                int j = y + dir[1];
                if(i < n && i >= 0 && j < n && j >= 0)
                    pq.offer(new int[]{i, j, grid[i][j]});
            }
        }
        return res;
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