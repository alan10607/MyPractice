package leetCode.java;

import java.util.*;

//BFS O(mn) O(mn)
class Solution542 {
    public int[][] updateMatrix(int[][] mat) {
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == 0){
                    queue.offer(new int[]{i, j});
                }else{
                    mat[i][j] = -1;//要更新的格子
                }
            }
        }

        while(!queue.isEmpty()){
            int[] posi = queue.poll();
            for(int[] dir : dirs){
                int i = posi[0] + dir[0];
                int j = posi[1] + dir[1];
                if(i < 0 || i >= m || j < 0 || j >= n || mat[i][j] != -1)
                    continue;

                mat[i][j] = mat[posi[0]][posi[1]] + 1;
                queue.offer(new int[]{i, j});
            }
        }
        return mat;
    }
}