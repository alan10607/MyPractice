package leetCode.java;

import java.util.*;

//BFS O(mn) O(mn)
class Solution286 {//lintcode663
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> q = new ArrayDeque<>();
        int m = rooms.length, n = rooms[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    q.offer(new int[]{i, j}); // 放入gate
                }
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int[] dir : dirs) {
                int i = pos[0] + dir[0];
                int j = pos[1] + dir[1];
                if (i < 0 || i >= m || j < 0 || j >= n || rooms[i][j] != Integer.MAX_VALUE) {
                    continue;
                }
                rooms[i][j] = rooms[pos[0]][pos[1]] + 1;
                q.offer(new int[]{i, j});
            }
        }
    }
}


//BFS O(mn) O(mn)
class Solution286_2 {//lintcode663
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