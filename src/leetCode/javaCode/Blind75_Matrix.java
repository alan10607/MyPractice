package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_Matrix {

    //Time Complexity: O(mn), Space Complexity: O(1)
    class Solution73 {
        public void setZeroes(int[][] matrix) {
            //題目要求用越少空間越好
            int m = matrix.length;
            int n = matrix[0].length;
            int other = 1;

            //先讀檔
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        if (i == 0) {
                            other = 0;//往左設定
                        } else {
                            matrix[i][0] = 0;//往左設定
                        }
                        matrix[0][j] = 0;//往上設定
                    }
                }
            }

            //先從內部開始, 否則第一行被設為0就不能拿來記了
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    //最右或最上為0
                    if (matrix[i][0] == 0 || matrix[0][j] == 0)
                        matrix[i][j] = 0;
                }
            }

            //matrix[0][0], 下方都設為0
            if (matrix[0][0] == 0) {
                for (int i = 0; i < m; i++)
                    matrix[i][0] = 0;
            }

            //額外空間為0, 右方都設為0
            if (other == 0) {
                for (int j = 0; j < n; j++)
                    matrix[0][j] = 0;
            }
        }
        /* 直接把第一行拿來記該列要不要為0, 就不會需要額外空間
        除了matrix[0][0]行列重複需要額外空間, 往左與上設定, 額外空間是左, matrix[0][0]是上

                    n                   n                   n
           [1]  1   1   0      [0]  1   0   0      [0]  0   0   0
            m   1   0   1  =>   m   0   0   1  =>   m   0   0   0
                1   1   1           1   1   1           1   0   0
        */
    }

    //Time Complexity: O(mn), Space Complexity: O(1)
    class Solution54 {
        public List<Integer> spiralOrder(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int top = 0;
            int bot = m - 1;
            int left = 0;
            int right = n - 1;
            List<Integer> res = new ArrayList<Integer>();

            //記得要用++top, 先加再判斷
            while(true){
                for(int i=left; i<=right; i++)
                    res.add(matrix[top][i]);
                if(++top > bot) break;

                for(int i=top; i<=bot; i++)
                    res.add(matrix[i][right]);
                if(left > --right) break;

                for(int i=right; i>=left; i--)
                    res.add(matrix[bot][i]);
                if(top > --bot) break;

                for(int i=bot; i>=top; i--)
                    res.add(matrix[i][left]);
                if(++left > right) break;
            }

            return res;
        }

        /*當left > right || top > right 時跳出
                    n                               n
                l           r                   l0  l1  r1  r0
            t   1   2   3   4           t0      1   2   3   4
        m       5   6   7   8   =>  m   t1 b1   5   6   7   8
            b   9   10  11  12          b0 t2   9   10  11  12

        */
    }

    //Time Complexity: O(mn), Space Complexity: O(1)
    class Solution48 {
        public void rotate(int[][] matrix) {
            //modify matrix in-place
            int l = 0;
            int r = matrix.length - 1;

            while(l < r){
                int t = l;
                int b = r;
                for(int i = 0; i <r - l; i++){
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
        由外圈往內圈, 直接右旋轉, 每邊需要轉 r - l次

                t, l+i
                1   2   3   4   t+i,r
                5   6   7   8
                9   10  11  12
        b-i, l  13  14  15  16
                        b, r-i

        */
    }

    //Time Complexity: O(mn 3^L), Space Complexity: O(mn)
    //L = word.length(), 每次進入dfs有3種(不含自己)的方向, 最多需要進mn次dfs, 故為mn3^L
    class Solution79 {
        public boolean exist(char[][] board, String word) {
            int m = board.length;
            int n = board[0].length;
            boolean[][] visited = new boolean[m][n];

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(dfs(board, i, j, 0, word, visited))
                        return true;
                }
            }

            return false;
        }

        public boolean dfs(char[][] board, int i, int j, int index, String word, boolean[][] visited){
            //設定例外
            if(visited[i][j] || board[i][j] != word.charAt(index)) return false;//若已經過或字母不合, 則退出
            if(index == word.length() - 1) return true;//滿足長度值

            visited[i][j] = true;//設為經過

            //開始進行遍歷
            int m = board.length;
            int n = board[0].length;
            //檢查邊界, 除了邊框外, word的長度也要檢查到
            if(index + 1 < word.length()){
                if(i + 1 <  m && dfs(board, i + 1, j, index + 1, word, visited)) return true;
                if(i - 1 >= 0 && dfs(board, i - 1, j, index + 1, word, visited)) return true;
                if(j + 1 <  n && dfs(board, i, j + 1, index + 1, word, visited)) return true;
                if(j - 1 >= 0 && dfs(board, i, j - 1, index + 1, word, visited)) return true;
            }

            visited[i][j] = false;//離開dfs設為未經過

            return false;
        }
    }

}