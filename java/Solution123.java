package leetCode.java;

//DP Stock O(n) O(1), n = prices.size()
class Solution123 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][3][2]; //[第i+1天][剩餘完成交易次數][0/1=是/否持有股票]
        for (int i = 0; i < n; ++i) {
            for (int j = 2; j > 0; --j) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[0];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }
        return dp[n - 1][2][0];
    }
}