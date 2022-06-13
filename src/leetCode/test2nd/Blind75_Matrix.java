package leetCode.test2nd;

import java.util.*;

public class Blind75_Matrix {

    //Simulation
    class Solution73 {
        public void setZeroes(int[][] matrix) {
            //A simple improvement uses O(m + n) space, but still not the best solution.
            int m = matrix.length;
            int n = matrix[0].length;
            boolean firstRowIsZero = false;
            //firstRowIsZero管matrix[0][j], matrix[0][0]管matrix[i][0]

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(matrix[i][j] == 0){
                        if(i == 0){
                            firstRowIsZero = true;
                        }else{
                            matrix[i][0] = 0;
                        }
                        matrix[0][j] = 0;
                    }
                }
            }

            for(int i=1; i<m; i++){
                for(int j=1; j<n; j++){
                    if(matrix[i][0] == 0 || matrix[0][j] == 0){
                        matrix[i][j] = 0;
                    }
                }
            }

            if(matrix[0][0] == 0){
                for(int i=0; i<m; i++)
                    matrix[i][0] = 0;
            }

            if(firstRowIsZero){
                for(int j=0; j<n; j++)
                    matrix[0][j] = 0;
            }
        }
    }

    //Simulation
    class Solution54 {
        public List<Integer> spiralOrder(int[][] matrix) {
            int t = 0;
            int b = matrix.length - 1;
            int l = 0;
            int r = matrix[0].length - 1;
            List<Integer> res = new ArrayList<>();

            while(true){
                for(int i=l; i<=r; i++)
                    res.add(matrix[t][i]);
                if(++t > b) break;

                for(int i=t; i<=b; i++)
                    res.add(matrix[i][r]);
                if(l > --r) break;

                for(int i=r; i>=l; i--)
                    res.add(matrix[b][i]);
                if(t > --b) break;

                for(int i=b; i>=t; i--)
                    res.add(matrix[i][l]);
                if(++l > r) break;
            }
            return res;
        }
    }

    //*Simulation
    class Solution48 {
        public void rotate(int[][] matrix) {
            int l = 0;
            int r = matrix.length - 1;

            while(l < r){
                int t = l;
                int b = r;
                int width = r - l;
                for(int i=0; i<width; i++){
                    int temp         = matrix[t][l + i];
                    matrix[t][l + i] = matrix[b - i][l];
                    matrix[b - i][l] = matrix[b][r - i];
                    matrix[b][r - i] = matrix[t + i][r];
                    matrix[t + i][r] = temp;
                }
                l++;
                r--;
            }
        }
        /*
                [t][l+i]
                1   2   3[t+i][r]
                4   5   6
        [b-i][l]7   8   9
                        [b][r-i]
        */
    }

    //DFS
    class Solution79 {
        public boolean exist(char[][] board, String word) {
            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(dfs(i, j, 0, board, word, visited))
                        return true;
                }
            }
            return false;
        }

        public boolean dfs(int i, int j, int k, char[][] board, String word, boolean[][] visited){
            if(visited[i][j] || word.charAt(k) != board[i][j]) return false;
            if(k == word.length() - 1) return true;//走到word最後

            visited[i][j] = true;

            int m = board.length;
            int n = board[0].length;
            if(i + 1 <  m && dfs(i + 1, j, k + 1, board, word, visited)) return true;
            if(i - 1 >= 0 && dfs(i - 1, j, k + 1, board, word, visited)) return true;
            if(j + 1 <  n && dfs(i, j + 1, k + 1, board, word, visited)) return true;
            if(j - 1 >= 0 && dfs(i, j - 1, k + 1, board, word, visited)) return true;

            visited[i][j] = false;

            return false;
        }
    }

}