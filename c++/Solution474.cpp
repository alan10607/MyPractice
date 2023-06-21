//2D-DP KP O(k(mn + S)) O(mn), k = strs.size(), S為str長度
class Solution474 {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));//m個0與n個1時的最大subset
        for(string str : strs){
            int zero = 0, one = 0;
            for(char ch : str) ch == '0' ? ++zero : ++one;

            for(int i=m; i>=zero; --i){//0-1背包問題, 內層逆序
                for(int j=n; j>=one; --j){
                    dp[i][j] = max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }
        return dp[m][n];
    }
};
/* ["100","1","1010"], m = 2, n = 2, 此為2D版的0-1背包問題

"100":
i\j 0   1   2
0   0   0   0   \
1   0   0   0    \
2   0   1   1     v(2,1)

"1":
i\j 0   1   2
0   0   1   1   -> (0,1)
1   0   1   1
2   0   1   2

"1010":
i\j 0   1   2
0   0   1   1   \
1   0   1   1    \
2   0   1   2     >(2,2)

*/