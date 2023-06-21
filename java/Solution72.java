package leetCode.java;

//2D-DP O(mn) O(mn)
class Solution72 {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i=1; i<=m; i++)
            dp[i][0] = i;//delete to match

        for(int j=1; j<=n; j++)
            dp[0][j] = j;//insert to match

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{//insert, delete, replace
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
/*
不做事: dp[i][j] = dp[i - 1][j - 1]
Insert: dp[i][j] = dp[i][j - 1] + 1
Delete: dp[i][j] = dp[i - 1][j] + 1
Replace: dp[i][j] = dp[i - 1][j - 1] + 1

word1 = "horse", word2 = "ros"

		r	o	s
	0	1	2	3
h	1	1	2	3
o	2	2	1	2
r	3	2	2	2
s	4	3	3	2
e	5	4	4	3
*/