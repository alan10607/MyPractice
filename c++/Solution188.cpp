//DP O(nk) O(1), n = prices.size()
class Solution188 {
public:
    int maxProfit(int k, vector<int>& prices) { // Solution123的完全體
        int n = prices.size();
        // <交易日期-1, 剩餘交易次數, 是否持有股票>
        vector<vector<vector<int>>> dp(n, vector<vector<int>>(k + 1, vector<int>(2)));
        for (int i = 0; i < n; ++i) {
            for (int j = k; j > 0; --j) {
                if (i == 0) { // base case
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }
};