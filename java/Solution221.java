package leetCode.java;

//2D-DP O(mn) O(mn)
class Solution221 {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int res = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(i == 0 || j == 0){
                    dp[i][j] = matrix[i][j] - '0';
                }else if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }
}