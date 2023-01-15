package leetCode.java;

import java.util.*;

//DP KP O(nk) O(n), n = coins.length, k = amount
class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);//amount+1表示無法組合成
        dp[0] = 0;
        for(int i=1; i<=amount; i++){
            for(int coin : coins){
                if(i >= coin)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
/* 可以重複出現 coins = [1,2,5], amount = 11
dp[0] = () = 0
dp[1] = min(dp[0]) + 1 = 1
dp[2] = min(dp[1], dp[0]) + 1 = 1
dp[3] = min(dp[2], dp[1]) + 1 = 2
dp[4] = min(dp[3], dp[4]) + 1 = 2
dp[5] = min(dp[4], dp[3], dp[0]) + 1 = 1
...
dp[n] = min(dp[n - coins[0]], ...)
*/