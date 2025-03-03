//2D-DP O(mn) O(mn)
class Solution115 {
public:
    int numDistinct(string s, string t) {
        int m = s.length(), n = t.length();
        // answer in 32-bit, 不然計算會超過, 本題dp value皆>0
        vector<vector<unsigned int>> dp(m + 1, vector<unsigned int>(n + 1, 0));

        for (int i = 0; i <= m; ++i) {
            dp[i][0] = 1; // 空字串必可被組合
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 如果是一樣, 就新增少這個字母的可能(左上), 否則就繼承(上)
                dp[i][j] = dp[i - 1][j] + (s[i - 1] == t[j - 1] ? dp[i - 1][j - 1] : 0);
            }
        }
        return dp[m][n];
    }
};
/* 子序列subsequences 考慮用DP

ex:
s = "babgbag", t = "bag"

dp[i][j]表示 s在[0,i]有幾種t在[0,j]的子序列
可以推導出轉移方程:
1. dp[i][0]一定是1, 因為所有字母都可組合出空字串
2. dp[i][j]要保持dp[i-1][j]的情況, 例如b有子序列b的話, ba也必定有子序列b
3. 如果s[i-1]==t[j-1], 即前一組字母一樣, 要繼承這些可能
例如bab可以組出2種b, 則bab_也可以組出2種b_, 例如babg可以組出兩種ba

    _   b   a   g
_   1   0   0   0
b   1   1   0   0
a   1   1   1   0
b   1   2   1   0
g   1   2   1   1
b   1   3   1   1
a   1   3   4   1
g   1   3   4   5



*/