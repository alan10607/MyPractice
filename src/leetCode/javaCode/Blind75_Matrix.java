package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_Matrix {

    //Time Complexity: O(mn), Space Complexity: O(1)
    class Solution {
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
}