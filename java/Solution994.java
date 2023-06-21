package leetCode.java;

import java.util.*;

//BFS O(mn) O(mn)
class Solution994 {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();//<i, j>, rotten oranges
        int fresh = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 2){
                    queue.offer(new int[]{i, j});
                }else if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }

        //BFS
        int res = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while(!queue.isEmpty() && fresh > 0){//fresh > 0, 避免計算最後一次無效步數
            res++;
            int size = queue.size();
            for(int k=0; k<size; k++){
                int[] root = queue.poll();
                for(int[] dir : dirs){
                    int i = root[0] + dir[0];
                    int j = root[1] + dir[1];
                    if(i < m && i >= 0 && j < n && j >= 0 && grid[i][j] == 1){
                        grid[i][j] = 2;
                        queue.offer(new int[]{i, j});
                        fresh--;
                    }
                }
            }
        }

        return fresh == 0 ? res : -1;
    }
}