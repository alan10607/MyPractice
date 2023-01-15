package leetCode.java;

import java.util.*;

//DP KP O(n^(0.5) * n) O(n)
class Solution279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];//dp[i]表示i最少可以由幾平方組成
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i * i <= n; ++i){//1,4,9.16...
            for(int j = i * i; j <= n; ++j){//完全背包
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n];
    }
}