//2D-DP O(mn) O(mn)
class Solution97 {
public:
    bool isInterleave(string s1, string s2, string s3) {
        int m = s1.length(), n = s2.length();
        if(m + n != s3.length()) return false;

        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1));
        dp[0][0] = true;
        for(int i=1; i<=m; ++i) dp[i][0] = dp[i - 1][0] && s1[i - 1] == s3[i - 1];
        for(int j=1; j<=n; ++j) dp[0][j] = dp[0][j - 1] && s2[j - 1] == s3[j - 1];

        for(int i=1; i<=m; ++i){
            for(int j=1; j<=n; ++j){//s1或s2的前綴為true且s1或s2也配對到字母時
                dp[i][j] =  (dp[i - 1][j] && s1[i - 1] == s3[i + j - 1])
                         || (dp[i][j - 1] && s2[j - 1] == s3[i + j - 1]);
            }
        }
        return dp[m][n];
    }
};
/* s1 = "aac", s2 = "dbb", s3 = "aadbbc"

        d   b   b
    T   F   F   F
a   T   F   F   F
a   T   T   T   T
c   F   F   F   T

*/