package leetCode.java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//BFS O(mn) O(mn)
class Solution1197 {
    public int minKnightMoves(int x, int y) {
        if(x == 0 && y == 0) return 0;

        int[][] dirs = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        Queue<int[]> q = new LinkedList<>();;
        q.offer(new int[]{0, 0});
        Set<int[]> visited = new HashSet<>();
        visited.add(new int[]{0, 0});
        int res = 0;
        while(!q.isEmpty()){
            res++;
            for(int k=q.size(); k>0; k--){
                int[] knight = q.poll();
                for(int[] dir : dirs){
                    int i = knight[0] + dir[0];
                    int j = knight[1] + dir[1];
                    int[] next = new int[]{i, j};
                    if(i == x && j == y)
                        return res;
                    if(i < 0 || j < 0 || i > x + 2 || j > y + 2 || visited.contains(next))
                        continue;

                    visited.add(next);
                    q.offer(next);
                }
            }
        }
        return -1;
    }
}