//DP O(nk) O(n), n = coins.length, k = amount
class Solution322 {
public:
    int coinChange(vector<int>& coins, int amount) {
        vector<int> dp(amount + 1, amount + 1);//amount + 1表示不能組出
        dp[0] = 0;//一開始為0種組合
        for(int i=1; i<=amount; ++i){
            for(int coin : coins){
                if(i >= coin)
                    dp[i] = min(dp[i], dp[i - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
};