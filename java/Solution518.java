package leetCode.java;

//DP Knapsack O(mn) O(n), m = coins.length, n = amount
class Solution518 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];// dp[i]代表i金額可以有幾種金幣組合
        dp[0] = 1; // 0元可以有一種方法湊出
        for (int coin : coins) { // 計算組合 Combination(2+2+1,1+2+2算同一種), coin loop放在外側
            for (int i = coin; i <= amount; ++i) { // 無限背包, 正序
                dp[i] += dp[i - coin]; // 每個dp[i]繼承去掉這枚硬幣的所有方法
            }
        }
        return dp[amount];
    }
}
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