package leetCode.java;

//DP O(n) O(1)
class Solution70 {
    public int climbStairs(int n) {
        int cur = 1, pre = 1;
        for (int i = 2; i <= n; ++i) {
            int temp = cur;
            cur = cur + pre;
            pre = temp;
        }
        return cur;
    }
}

/*
dp[0]=1
dp[1]=1
dp[2]=dp[1]+dp[0]=2
dp[3]=dp[2]+dp[1]=3
...

dp[n] = dp[n-1] + dp[n-2]

*/