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

}