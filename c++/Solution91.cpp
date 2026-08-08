//DP O(n) O(n)
class Solution91 {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            // s[i-1] 單獨解碼: 1~9
            if (s.charAt(i - 1) > '0') {
                dp[i] += dp[i - 1];
            }

            // s[i-2 ~ i-1] 一起解碼: 10~26
            if (i >= 2 &&
                (s.charAt(i - 2) == '1' ||
                    (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
/* s = "226"
dp[0]=('')=1
dp[1]=(2)=1
dp[2]=(2 2, 22)=2
dp[3]=(2 2 6, 226, 2 26)=3
*/
/*
s = "226"
dp[i] = 前 i 個字元有幾種解碼方式

dp[0]=('')=1
dp[1]=(2)=1
dp[2]=(2 2, 22)=dp[1]+dp[0]=1+1=2
dp[3]=(2 2 6, 22 6, 2 26)=dp[2]+dp[1]=2+1=3
但前提是前兩位數要在[10,26], 前一位數是在[1,9]

所以轉移方程式:
if (s[i-1] > '0') {
    dp[i] += dp[i-1]
}

if (10 <= s[i-2 ~ i-1] <= 26) {
    dp[i] += dp[i-2]
}

*/