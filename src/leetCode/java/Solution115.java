package leetCode.java;

//2D-DP O(mn) O(mn)
class Solution115 {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i=0; i<=m; i++)
            dp[i][0] = 1;//空字串一定可以完成

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
}
/*
若不同, 則繼承之前有辦法組合的可能
若相同, 則除了繼承(上)外也要繼承少一個字母的(左上), 相當於獲得少一個字母的dp

		a	a	b
	1	0	0	0
a	1 	1	0	0
a 	1	2 	1	0
a	1	3	3	0
b	1	3	3	3
b	1	3	3	6
*/