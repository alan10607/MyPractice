package leetCode.java;

import java.util.*;

//BFS O(mn) O(mn)
class Solution286 {//lintcode663
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();//<[x, y], ...>
        int m = rooms.length;
        int n = rooms[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(rooms[i][j] == 0)
                    queue.offer(new int[]{i, j});
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int level = 0;
        while(!queue.isEmpty()){
            level++;
            int size = queue.size();
            for(int k=0; k<size; k++){
                int[] posi = queue.poll();
                for(int[] dir : dirs){
                    int i = posi[0] + dir[0];
                    int j = posi[1] + dir[1];
                    if(i < m && i >= 0 && j < n && j >= 0 && rooms[i][j] == Integer.MAX_VALUE){
                        rooms[i][j] = level;//改在檢查時就visited否則會Memory Limit Exceeded
                        queue.offer(new int[]{i, j});
                    }
                }
            }
        }
    }
}