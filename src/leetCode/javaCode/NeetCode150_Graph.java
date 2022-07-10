package leetCode.javaCode;

import java.util.*;

/**
 *  Graphs
 */
public class NeetCode150_Graph {

    //Time Complexity: O(mn), Space Complexity: O(mn)
    //DFS
    class Solution695 {
        public int maxAreaOfIsland(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int res = 0;
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    res = Math.max(res, dfs(i, j, grid));
                }
            }
            return res;
        }

        public int dfs(int i, int j, int[][] grid){
            if(grid[i][j] == 0 || grid[i][j] == -1) return 0;

            grid[i][j] = -1;//表示visited
            int m = grid.length;
            int n = grid[0].length;
            int area = 1;
            //搜集上下左右的大小
            if(i + 1 <  m) area += dfs(i + 1, j, grid);
            if(i - 1 >= 0) area += dfs(i - 1, j, grid);
            if(j + 1 <  n) area += dfs(i, j + 1, grid);
            if(j - 1 >= 0) area += dfs(i, j - 1, grid);
            return area;
        }
    }

    //Time Complexity: O(mn), Space Complexity: O(mn)
    //DFS
    class Solution130 {
        public void solve(char[][] board) {
            int m = board.length;
            int n = board[0].length;

            //把邊界設為*
            for(int i=0; i<m; i++){
                dfs(i, 0, board);
                dfs(i, n - 1, board);
            }
            for(int j=0; j<n; j++){
                dfs(0, j, board);
                dfs(m - 1, j, board);
            }

            //整理board
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(board[i][j] == '*'){
                        board[i][j] = 'O';
                    }else if(board[i][j] == 'O'){
                        board[i][j] = 'X';
                    }
                }
            }
        }

        public void dfs(int i, int j, char[][] board){
            if(board[i][j] != 'O') return;

            board[i][j] = '*';//表示相鄰邊界

            int m = board.length;
            int n = board[0].length;
            if(i + 1 <  m) dfs(i + 1, j, board);
            if(i - 1 >= 0) dfs(i - 1, j, board);
            if(j + 1 <  n) dfs(i, j + 1, board);
            if(j - 1 >= 0) dfs(i, j - 1, board);
        }
    }

    //Time Complexity: O(mn), Space Complexity: O(mn)
    //BFS
    class Solution994 {
        public int orangesRotting(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int fresh = 0;
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            Queue<int[]> queue = new LinkedList<>();
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    //0 = empty, 1 = fresh, 2 = rotten
                    if(grid[i][j] == 1){
                        fresh++;
                    }else if(grid[i][j] == 2){
                        queue.offer(new int[]{i, j});
                    }
                }
            }

            //開始BFS
            int time = 0;
            while(!queue.isEmpty() && fresh > 0){//fresh > 0, 避免計算最後一次無效步數
                time++;
                int size = queue.size();
                for(int k=0; k<size; k++){
                    int[] rotten = queue.poll();
                    int i = rotten[0];
                    int j = rotten[1];

                    for(int[] dir : dirs){
                        int nextI = i + dir[0];
                        int nextJ = j + dir[1];
                        if(nextI < m && nextI >= 0 && nextJ < n && nextJ >= 0 && grid[nextI][nextJ] == 1){
                            grid[nextI][nextJ] = 2;
                            fresh--;
                            queue.offer(new int[]{nextI, nextJ});
                        }
                    }
                }
            }

            return fresh == 0 ? time : -1;
        }
    }

    //Time Complexity: O(mn), Space Complexity: O(mn)
    //BFS
    class Solution286 {
        public void wallsAndGates(int[][] rooms) {
            int m = rooms.length;
            int n = rooms[0].length;
            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (rooms[i][j] == 0) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }

            //start BFS
            int distance = 0;
            int empty = 2147483647;
            int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            while (!queue.isEmpty()) {
                distance++;
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    int[] room = queue.poll();
                    int i = room[0];
                    int j = room[1];

                    for (int[] dir : dirs) {
                        int nextI = i + dir[0];
                        int nextJ = j + dir[1];
                        if (nextI < m && nextI >= 0 && nextJ < n && nextJ >= 0 && rooms[nextI][nextJ] == empty) {
                            rooms[nextI][nextJ] = distance;
                            queue.offer(new int[]{nextI, nextJ});
                        }
                    }
                }
            }
        }
    }

    //Time Complexity: O(V + E), Space Complexity: O(V + E), V = vertices = numCourses,  E = edges = prerequisites.length
    //Topological sorting
    class Solution210 {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            //1 edges
            Map<Integer, List<Integer>> edges = new HashMap<>();
            int[] counts = new int[numCourses];
            for(int[] pre : prerequisites){
                if(!edges.containsKey(pre[1]))
                    edges.put(pre[1], new ArrayList<Integer>());

                edges.get(pre[1]).add(pre[0]);//<先修, 後修>
                counts[pre[0]]++;
            }

            //2 find start
            Deque<Integer> queue = new LinkedList<>();
            for(int i=0; i<counts.length; i++){
                if(counts[i] == 0)
                    queue.offer(i);//為最父node或是無任何連接的node會先被走到
            }

            //3 run
            int[] res = new int[numCourses];
            int nodeCount = 0;
            while(!queue.isEmpty()){
                int node = queue.poll();
                res[nodeCount] = node;
                nodeCount++;

                if(edges.containsKey(node)){
                    List<Integer> children = edges.get(node);
                    for(int child : children){
                        counts[child]--;
                        if(counts[child] == 0)
                            queue.offer(child);
                    }
                }
            }

            return nodeCount == numCourses ? res : new int[]{};
        }
    }

}