//2D-DP O(n) O(n), n = prices.size()
class Solution309 {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        vector<vector<int>> dp(n, vector<int>(2)); // <交易日期-1, 是否持有股票>
        dp[0][0] = 0; // base case
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            if (i == 1) { // base case2
                // 還沒隔兩天, 維持前一天狀態已做buy, 或昨天cool今天才buy
                dp[i][1] = max(dp[i - 1][1], -prices[1]);
            } else { // i > 1
                // 賣出後要隔一天才能買, 賣出後的狀態是無股票的dp[i][0], 在這裡要用前天的i-2
                dp[i][1] = max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
            cout << dp[i][0] << dp[i][1] << "\n";
        }
        return dp[n - 1][0];
    }
};


// 2D-DP O(n) O(1)
class Solution309_2 {
public:
    int maxProfit(vector<int>& prices) {
        int buy = -prices[0], sell = 0, cool = 0;
        for (int i = 1; i < prices.size(); ++i) {
            int new_buy = max(buy, cool - prices[i]);
            int new_sell = buy + prices[i];
            int new_cool = max(cool, sell);
            buy = new_buy;
            sell = new_sell;
            cool = new_cool;
        }
        return max(sell, cool);
    }
};
/*
                cool
        buy             cool
    sell    buy
    cool
*/