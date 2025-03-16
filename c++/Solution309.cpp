//2D-DP O(n) O(n), n = prices.size()
class Solution309 {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        vector<vector<int>> dp(n, vector<int>(2)); // <交易日期-1, 是否持有股票>
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            if (i >= 2) {
                // 賣出後要隔一天才能買, 賣出後的狀態是無股票的dp[i][0], 在這裡要用前天的i-2
                dp[i][1] = max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
        }
        return dp[n - 1][0];
    }
};


//2D-DP O(n) O(1), n = prices.size()
class Solution309 {
public:
    int maxProfit(vector<int>& prices) {
        int buy = -prices[0], sell = 0, cool = 0;
        for(int i=1; i<prices.size(); ++i){
            int newBuy = max(cool - prices[i], buy);
            int newSell = buy + prices[i];
            int newCool = max(sell, cool);
            buy = newBuy;
            sell = newSell;
            cool = newCool;
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