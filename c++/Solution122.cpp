//Greedy DP O(n) O(1), n = prices.size()
class Solution122 {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0;
        for (int i = 1; i < prices.size(); ++i) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }
};
/* ex: prices = [7,1,5,3,6,4]

7   1   5   3   4   6
|
|                   |
|       |           |
|       |       |   |
|       |   |   |   |
|       |   |   |   |
|   |   |   |   |   |

        +4      +1  +2
res=7


如果今天比昨天大, 就立刻賣出
如果今天比較小, 就不動作
*/


//DP O(n) O(n)
class Solution122_2 {
public:
    int maxProfit(vector<int>& prices) {
        int n = prices.size();
        vector<vector<int>> dp(n, vector<int>(2)); // <第幾天做的交易, 是否持有股票>
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0]; // 賣掉沒股票的狀態會較多
    }
};
/*
dp[i][k][0 or 1]
i表示第 i+1 天做的交易, 0 <= i <= 總交易天數 - 1
k表示剩餘的交易次數,  0 <= k <= 總交易數 - 1
0 or 1代表是否持有股票

則轉移方程式:
dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
不持有股票 = max(不持有然後不做事, 持有股票今天賣出)

dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
持有股票 = max(持有然後不做事, 不持有股票今天買入)


本題k無限制

dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
*/