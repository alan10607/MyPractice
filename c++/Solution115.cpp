//2D-DP O(mn) O(mn)
class Solution115 {
public:
    int numDistinct(string s, string t) {
        int m = s.length(), n = t.length();
        vector<vector<unsigned int>> dp(m + 1, vector<unsigned int>(n + 1, 0));//answer in 32-bit, 不過計算會超過

        for(int i=0; i<=m; ++i)
            dp[i][0] = 1;//空字串必可被組合

        for(int i=1; i<=m; ++i){
            for(int j=1; j<=n; ++j){//如果是一樣, 就新增少這個字母的可能(左上), 否則就繼承(上)
                dp[i][j] = dp[i - 1][j] + (s[i - 1] == t[j - 1] ? dp[i - 1][j - 1] : 0);
            }
        }
        return dp[m][n];
    }
};
/*
        r   a   b   i
    1   0   0   0   0
r   1   1   0   0   0
a   1   1   1   0   0
b   1   1   1   1   0
b   1   1   1   2   0
b   1   1   1   3   0

*/