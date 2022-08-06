package leetCode.java;

//DP O(n) O(1)
class Solution70 {
    public int climbStairs(int n) {
        int one = 1;
        int two = 0;
        for(int i=1; i<=n; i++){
            int temp = one;
            one = one + two;
            two = temp;
        }
        return one;
    }
}
/*
dp[0] = 1
dp[1] = dp[0] = 1
dp[2] = dp[0] + dp[1] = 2
dp[3] = dp[1] + dp[2] = 3
...
dp[n] = dp[n-2] + dp[n-1]
*/