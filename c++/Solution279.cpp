//DP KP O(n^(0.5) * n) O(n)
class Solution279 {
public:
    int numSquares(int n) {
        vector<int> dp(n + 1, INT_MAX);//dp[i]表有最少的幾個數組成
        dp[0] = 0;//不需數字來組合
        for(int i = 1; i * i <= n; ++i){//背包內物品1,4,16...
            for(int j = i * i; j <= n; ++j){//完全背包, 正序
                dp[j] = min(dp[j], dp[j - i * i] + 1);//求最少組合
            }
        }
        return dp[n];
    }
};
/* n = 12, dp初始化為INT_MAX, 不用擔心溢出因為內層第一個跑1會覆蓋
    0   1   2   3   4   5   6   7   8   9   10  11  12
1   0   1   2   3   4   5   6   7   8   9   10  11  12
4   0   1   2   3   1   2   3   4   2   3   4   5   3
9   0   1   2   3   1   2   3   4   2   1   2   3   3

*/