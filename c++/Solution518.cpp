//DP KP O(mn) O(n), m = coins.size(), n = amount
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
/* amount = 5, coins = [1,2,3]
1+1+1+2與1+2+1+1視為同一種, 為背包問題

    0   1   2   3   4   5
1   1   1   1   1   1   1
2   1   1   2   2   3   3
3   1   1   2   3   4   5

*/