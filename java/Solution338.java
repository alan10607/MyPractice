package leetCode.java;

//Bit DP O(n) O(1)
class Solution338 {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}
/*
dp[i]表示數字i時的1個數
dp[i] = [除了最後其他數][最後一位數]
ex:
1001 -> [100] + [1]

dp  bit
0   0       0
1   1       1
2   10      dp[1]+0
3   11      dp[1]+1
4   100     dp[2]+0
5   101     dp[2]+1
6   110     dp[3]+0
7   111     dp[3]+1
8   1000    dp[4]+0
9   1001    dp[4]+1

dp[i] = dp[去掉最後一位數]+(最後一位是1 ? 1 : 0)
      = dp[i >> 1] + (i & 1)
*/


//Bit DP O(n) O(1)
class Solution338_2 {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        int base = 0; // 2^n的數
        for (int i = 1; i <= n; ++i) {
            if ((i & (i - 1)) == 0) { // 難點在於怎麼判斷是2^n
                base = i;
            }
            dp[i] = 1 + dp[i - base];
        }
        return dp;
    }
}
/*
dp[i]表示數字i時的1個數
當i為2^n時, dp[i]=1
難點在於怎麼判斷是2^n, 可以用i&(i-1) ex: 8 & 7 = 1000 & 0111 = 0

dp[0] =     0 = 0
dp[1] =     1 = 1
dp[2] =    10 = 1 + dp[0] = 1
dp[3] =    11 = 1 + dp[1] = 2
dp[4] =   100 = 1 + dp[0] = 1
dp[5] =   101 = 1 + dp[1] = 2
dp[6] =   110 = 1 + dp[2] = 2
dp[7] =   111 = 1 + dp[3] = 3
dp[8] =  1000 = 1 + dp[0] = 1
dp[9] =  1001 = 1 + dp[1] = 2
...
dp[n] = 1 + dp[最高為以外的bit]
*/