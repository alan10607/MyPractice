package leetCode.java;

//DP O(mn) O(mn)
class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        //  dp[i][0] = dp[0][j] = 0, 其中一個text長度為0時候, LCS也會是0

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }
}
/*
dp[i][j]表示s1 前i個字元和 s2 前j個字元的LCS 長度
兩種情況:
1. 相同則LCS增加
    s1[i-1]==s2[j-1], 則dp[i][j]=dp[i-1][j-1] + 1, 

2. 若不同則應該繼承較多可能的LCS
    s1[i-1]==s2[j-1], 則dp[i][j]=max(dp[i][j-1],dp[i-1][j])

ex: text1 = "abcde", text2 = "ace" 

        a   c   e
    0   0   0   0
a   0   1   1   1
b   0   1   1   1
c   0   1   2   2
d   0   1   2   2
e   0   1   2   3

*/