//DP KP O(nk) O(n), n = coins.size(), k = amount
class Solution322 {
public:
    int coinChange(vector<int>& coins, int amount) {
        vector<int> dp(amount + 1, amount + 1);//amount + 1表示不能組出
        dp[0] = 0;//一開始為0種組合
        for(int i=1; i<=amount; ++i){//完全背包問題
            for(int coin : coins){
                if(i >= coin)
                    dp[i] = min(dp[i], dp[i - coin] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
};
/* coins = [1,2,5], amount = 11, 預設為amount+1表示不可能, 或是可以用INT_MAX-1
    0   1   2   3   4   5   6   7   8   9   10  11
    0   12  12  12  12  12  12  12  12  12  12  12
1   0   1   2   3   4   5   6   7   8   9   10  11
2   0   1   1   2   2   3   3   4   4   5   5   6
5   0   1   1   2   2   1   2   2   3   3   2   3

*/

/*
0-1背包問題:
不重複, 是否可以取出target, 內層反序

完全背包問題:
可重複, 是否可以取出target, 內層正序

*/