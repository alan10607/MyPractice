package leetCode.java;

//2D-DP O(mn) O(mn)
class Solution97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (i > 0) {
                    dp[i][j] |= s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j];
                }
                if (j > 0) {
                    dp[i][j] |= s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1];
                }
            }   
        }
        return dp[m][n];
    }
}
/*

dp[i][j]代表:
s1 前 i 個字元 + s2 前 j 個字元, 能不能組成 s3 前 i+j 個字元

dp[0][0] = true, 因為 空的 s1 + 空的 s2 = 空的 s3

dp[i][j] =
    (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j])
    ||
    (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1])

ex: s1 = "aac", s2 = "dbb", s3 = "aadbbc"

        j   0   1   2   3
                d   b   b
i
0           T   F   F   F
1   a       T   F   F   F
2   a       T   T   T   T
3   c       F   F   F   T

res=true
*/
