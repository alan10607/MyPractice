//2D-DP O(mn) O(mn)
class Solution221 {
public:
    int maximalSquare(vector<vector<char>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        vector<vector<int>> dp(m, vector<int>(n, 0));
        int res = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j] - '0';
                }else if(matrix[i][j] == '1'){//matrix == '1' && i > 0 && j > 0
                    dp[i][j] = min(min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;//上,左,左上
                }
                res = max(res, dp[i][j]);
            }
        }
        return res * res;//要回傳的是面積
    }
};
/*
0   1   1   1
1   1   1   1
1   1   1   1
0   1   1   1

matrix[i][j] == '1'時, dp[i][j]=min(dp上,dp左,dp左上)+1
0   1   1   1
1   1   2   2
1   2   2   3
0   1   2   3
*/