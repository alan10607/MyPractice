//DP Knapsack O(mn) O(n), m = coins.size(), n = amount
class Solution518 {
public:
    int change(int amount, vector<int>& coins) {
        vector<int> dp(amount + 1, 0);
        dp[0] = 1;
        for(int coin : coins){
            for(int i=coin; i<=amount; ++i){//完全背包問題, 內層正序
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
};
/*
coins = [1,2,5], amount = 11, 預設為dp[0]=1, 0元可以有一種方法湊出

           1----------------->
            0   1   2   3   4   5   6   7   8   9   10  11
2   init    1   0   0   0   0   0   0   0   0   0   0   0
|   1           1   1   1   1   1   1   1   1   1   1   1
|   2               2   2   3   3   4   4   5   5   6   6
|   5                           4   5   6   7   8   10  11
v


更新方向: 先跑amount再跑coin

*/