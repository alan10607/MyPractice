package leetCode.java;

import java.util.*;

//2D-DP O(mn) O(mn)
class Solution329 {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for(int i=0; i<m; i++)
            Arrays.fill(dp[i], -1);//預設-1, 0 <= matrix[i][j] <= 231 - 1

        int res = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                res = Math.max(res, dfs(i, j, -1, dp, matrix));
            }
        }
        return res;
    }

    public int dfs(int i, int j, int last, int[][] dp, int[][] matrix){
        if(last >= matrix[i][j]) return 0;//等於也return
        if(dp[i][j] != -1) return dp[i][j];

        int m = matrix.length;
        int n = matrix[0].length;
        int lip = 1;
        if(i + 1 <  m) lip = Math.max(lip, dfs(i + 1, j, matrix[i][j], dp, matrix) + 1);
        if(i - 1 >= 0) lip = Math.max(lip, dfs(i - 1, j, matrix[i][j], dp, matrix) + 1);
        if(j + 1 <  n) lip = Math.max(lip, dfs(i, j + 1, matrix[i][j], dp, matrix) + 1);
        if(j - 1 >= 0) lip = Math.max(lip, dfs(i, j - 1, matrix[i][j], dp, matrix) + 1);
        dp[i][j] = lip;//這裡才更新dp
        return dp[i][j];
    }
}