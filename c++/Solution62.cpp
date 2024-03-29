//2D-DP O(mn) O(mn)
class Solution62 {
public:
    int uniquePaths(int m, int n) {
        vector<vector<int>> dp(m, vector<int>(n, 0));
        for(int i=0; i<m; ++i) dp[i][0] = 1;
        for(int j=0; j<n; ++j) dp[0][j] = 1;

        for(int i=1; i<m; ++i){
            for(int j=1; j<n; ++j){
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
};
/* ex:
0   0   0   0   0
0   1   1   1   1
0   1   2   3   4
0   1   3   6   10
*/