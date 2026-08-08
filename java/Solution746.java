package leetCode.java;

//DP O(n) O(1)
class Solution746 {
    public int minCostClimbingStairs(int[] cost) {
        int cur = 0, pre = 0;
        // i<2可以直接走到, 最多要計算到cost.length, 計算範圍是[2, n]
        for (int i = 2; i <= cost.length; ++i) {
            int temp = cur;
            cur = Math.min(cur + cost[i - 1], pre + cost[i - 2]);
            pre = temp;
        }
        return cur;
    }
}
/*       0  1  2
cost = [10,15,20] 前兩格都可以直接走到
dp[0] = 0
dp[1] = 0
dp[2] = min(dp[0] + 10, dp[1] + 15) = 10
dp[3] = min(dp[1] + 15, dp[2] + 20) = 15
...
dp[n] = min(dp[n-1] + cost[n-1], dp[n-2] + cost[n-2])
*/