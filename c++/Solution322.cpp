//DP KP O(nk) O(n), n = coins.size(), k = amount
class Solution322 { // 自底向上
public:
    int coinChange(vector<int>& coins, int amount) {
        vector<int> dp(amount + 1, amount + 1); // amount + 1表示不能組出, 如果用INT_MAX, 要小心overflow
        dp[0] = 0; // 一開始為0種組合
        for (int i = 1; i <= amount; ++i) { // 完全背包問題
            for (int coin : coins) {
                if (i - coin >= 0) { // 若初始為INT_MAX, 要加上 dp[i - coin] != INT_MAX, 避免INT_MAX + 1的情況出現
                    dp[i] = min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
};
/* 此為自底向上的解法, 還有另一種解法是自頂向下
coins = [1,2,5], amount = 11, 預設為amount+1表示不可能, 或是可以用INT_MAX-1
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


//DP KP O(nk) O(n), n = coins.size(), k = amount
class Solution322_2 { // 透過自頂向下的遞歸也可以解這題
public:
    vector<int> dp;
    int coinChange(vector<int>& coins, int amount) {
        dp = vector<int>(amount + 1, INT_MAX); // INT_MAX表未被紀錄, -1表無法湊出
        dp[0] = 0;
        return backtracking(coins, amount);
    }

    int backtracking(vector<int>& coins, int amount) {
        if (dp[amount] != INT_MAX) return dp[amount];

        int res = INT_MAX;
        for (int coin : coins) {
            if (amount >= coin) {
                int num = backtracking(coins, amount - coin); // 計算子問題
                if (num != -1) {
                    res = min(res, num + 1);
                }
            }
        }
        dp[amount] = (res == INT_MAX) ? -1 : res; // 在這裡才要更新dp, 判斷該dp是否有解
        return dp[amount];
    }
};