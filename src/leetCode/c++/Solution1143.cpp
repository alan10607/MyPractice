//DP O(mn) O(mn)
class Solution1143 {
public:
    int longestCommonSubsequence(string text1, string text2) {
        int m = text1.length(), n = text2.length();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1, 0));
        for(int i=0; i<m; ++i)
            dp[i][0] = 0;

        for(int j=1; j<n; ++j)
            dp[0][j] = 0;

        for(int i=1; i<=m; ++i){
            for(int j=1; j<=n; ++j){
                if(text1[i - 1] == text2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
};
/*
        a   c   e
    0   0   0   0
a   0   1   1   1
b   0   1   1   1
c   0   1   2   2
d   0   1   2   2
e   0   1   2   3

*/