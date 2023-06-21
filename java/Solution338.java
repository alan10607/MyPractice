package leetCode.java;

//Bit DP O(n) O(1)
class Solution338 {
    public int[] countBits(int n) {
        //如果(n & (n-1)) == 0, 剛好會是00100...的情況
        int[] dp = new int[n + 1];
        int highBit = 0;//最高bit的數值
        for(int i=1; i<=n; i++){
            if((i & (i - 1)) == 0)
                highBit = i;

            dp[i] = 1 + dp[i - highBit];
        }
        return dp;
    }
}
/*
dp[0] =     0 = 0
dp[1] =     1 = 1
dp[2] =    10 = 1 + dp[0] = 1
dp[3] =    11 = 1 + dp[1] = 2
dp[4] =   100 = 1 + dp[0] = 1
dp[5] =   101 = 1 + dp[1] = 2
dp[6] =   110 = 1 + dp[2] = 2
dp[7] =   111 = 1 + dp[3] = 3
...
dp[n] = 1 + dp[最高為以外的bit]
*/