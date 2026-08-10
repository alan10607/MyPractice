package leetCode.java;

import java.util.*;

//2D-DP O(mn) O(mn)
class Solution329 {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, res = 0;;
        int[][] dp = new int[m][n]; // Memoization記住算過的lip, 否則會TLE
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], -1); // 都先設為-1表無法到達
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res = Math.max(res, dfs(i, j, -1, dp, matrix));
            }
        }
        return res;
    }

    public int dfs(int i, int j, int last, int[][] dp, int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || last >= matrix[i][j]) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int lip = 0;
        for (int[] dir : dirs) {
            lip = Math.max(lip, dfs(i + dir[0], j + dir[1], matrix[i][j], dp, matrix) + 1);
        }
        dp[i][j] = lip; // 這裡才更新dp, 避免dfs沒算完就被污染
        return lip;
    }
}