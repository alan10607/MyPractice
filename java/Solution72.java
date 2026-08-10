package leetCode.java;

//2D-DP O(mn) O(mn)
class Solution72 {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            dp[i][0] = i; // delete to match
        }
        for (int j = 0; j <= n; ++j) {
            dp[0][j] = j; // insert to match
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else { // min(新,刪,改) + 1
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
/*
dp[i][j] = 把 word1 前 i 個字元
           轉換成 word2 前 j 個字元
           所需要的最少操作次數

dp[i][0] = i, 全部 delete
dp[0][j] = j, 全部 insert

if 字母一樣:
    i,j往前一個不增加動作次數
else:
    選擇 min(新,刪,改)中最小的+1, 其中
    dp[i-1][j]+1代表刪除word1
    dp[i][j+1]+1代表新增word2
    dp[i-1][j-1]+1代表修改word1某字母到word2



ex: word1 = "horse", word2 = "ros"

        r   o   s
    0   1   2   3
h   1   1   2   3
o   2   2   1   2
r   3   2   2   2
s   4   3   3   2
e   5   4   4   3

*/