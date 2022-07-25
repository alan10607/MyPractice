package leetCode.java;

//DP O(mn) O(mn)
class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }
}
/* text1, text2沒有大小關係, 空字串跟其它字母都不會有Common, 既使是空字串與空字串也是0
		a	c	d	e
	0	0	0	0	0
a	0	1	1	1	1
b	0	1	1	1	1
c	0	1	2	2	2
e	0	1	2	2	3
如果字母相同則 左上角+1, 如果不同則 max(上,左)
*/
