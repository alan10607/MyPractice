package leetCode.java;

import java.util.*;

//BFS O(mn) O(mn)
class Solution994 {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new ArrayDeque<>(); // <[i, j]>, rotten oranges
        int m = grid.length, n = grid[0].length, fresh = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 2) { // 0=empty, 1=fresh, 2=rotten
                    q.offer(new int[]{i, j});
                } else if (grid[i][j] == 1){
                    ++fresh;
                }
            }
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int time = 0;
        while (!q.isEmpty() && fresh > 0) { // 若沒有判斷fresh>0, 最後一輪會只剩下爛掉的, 其實根本不用再跑一次
            for (int k = q.size(); k > 0; --k) {
                int[] pos = q.poll();
                for (int[] dir : dirs) {
                    int i = pos[0] + dir[0];
                    int j = pos[1] + dir[1];
                    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
                        continue;
                    }
                    grid[i][j] = 2;
                    --fresh; // 又爛了一顆
                    q.offer(new int[]{i, j});
                }
            }
            ++time;
        }
        return (fresh == 0) ? time : -1;
    }
}