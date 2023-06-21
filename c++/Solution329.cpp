//2D-DP O(mn) O(mn)
class Solution329 {
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size(), res = 0;
        vector<vector<int>> dp(m, vector<int>(n, -1));
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                res = max(res, dfs(i, j, -1, dp, matrix));
            }
        }
        return res;
    }

    int dfs(int i, int j, int last, vector<vector<int>>& dp, vector<vector<int>>& matrix){
        int m = matrix.size(), n = matrix[0].size();
        if(i < 0 || i >= m || j < 0 || j >= n || last >= matrix[i][j]) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int lip = 1;
        for(auto dir : dirs)
            lip = max(lip, dfs(i + dir.first, j + dir.second, matrix[i][j], dp, matrix) + 1);

        dp[i][j] = lip;
        return lip;
    }
};