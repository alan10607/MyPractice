package leetCode.java;

import java.util.*;

//BFS O(mn) O(mn)
class Solution1730 {
    public int getFood(char[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == '*'){
                    q.offer(new int[]{i, j});
                    grid[i][j] = 'X';//設為visited
                    break;
                }
            }
        }

        //*=start, #=food, X=block, O=empty
        int res = 0;
        while(!q.isEmpty()){
            res++;
            for(int k=q.size(); k>0; k--){
                int[] node = q.poll();
                for(int[] dir : dirs){
                    int i = node[0] + dir[0];
                    int j = node[1] + dir[1];
                    if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 'X') continue;
                    if(grid[i][j] == '#') return res;

                    grid[i][j] = 'X';//設為visited
                    q.offer(new int[]{i, j});
                }
            }
        }
        return -1;
    }
}