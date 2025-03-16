//DP O(n) O(1), n = prices.size()
class Solution123 {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        // <交易日期-1, 剩餘交易次數, 是否持有股票>
        vector<vector<vector<int>>> dp(n, vector<vector<int>>(3, vector<int>(2)));

        for (int i = 0; i < n; ++i) {
            for (int k = 2; k > 0; --k) { // 要反向計算k, 因為dp[i][k][...]是基於dp[i][k-1][...]
                if (i == 0) { // 設定base case
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[0];
                    continue;
                }
                dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][2][0]; //取較多交易次數的, 交易次數多的利潤會>=交易次數較少的
    }
};