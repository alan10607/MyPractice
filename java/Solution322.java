package leetCode.java;

import java.util.*;

//DP Knapsack O(nk) O(n), n = coins.length, k = amount
class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // amount + 1表示不能組出, 如果用Integer.MAX_VALUE, 要小心overflow
        dp[0] = 0;
        for (int i = 1; i <= amount; ++i) {
            for (int coin : coins) {
                if (i >= coin) {
                    // 若初始為Integer.MAX_VALUE, 要加上 dp[i - coin] != Integer.MAX_VALUE, 避免Integer.MAX_VALUE + 1的情況出現
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); 
                }
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
/* 此為自底向上的解法, 還有另一種解法是自頂向下
coins = [1,2,5], amount = 11, 預設為amount+1表示不可能, 或是可以用INT_MAX-1
    0   1   2   3   4   5   6   7   8   9   10  11
    0   12  12  12  12  12  12  12  12  12  12  12
1   0   1   2   3   4   5   6   7   8   9   10  11
2   0   1   1   2   2   3   3   4   4   5   5   6
5   0   1   1   2   2   1   2   2   3   3   2   3

*/