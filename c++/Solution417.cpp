//DFS O(mn) O(mn)
class Solution417 {
public:
    vector<vector<int>> pacificAtlantic(vector<vector<int>>& heights) {
        int m = heights.size(), n = heights[0].size();
        vector<vector<bool>> pac(m, vector<bool>(n));
        vector<vector<bool>> atl(m, vector<bool>(n));
        for(int i=0; i<m; ++i)
            dfs(i, 0, pac, heights, -1);

        for(int j=0; j<n; ++j)
            dfs(0, j, pac, heights, -1);

        for(int i=0; i<m; ++i)
            dfs(i, n - 1, atl, heights, -1);

        for(int j=0; j<n; ++j)
            dfs(m - 1, j, atl, heights, -1);

        vector<vector<int>> res;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(pac[i][j] && atl[i][j])
                    res.push_back({i, j});
            }
        }
        return res;
    }

    void dfs(int i, int j, vector<vector<bool>>& ocean, vector<vector<int>>& heights, int last){
        int m = heights.size(), n = heights[0].size();
        if(i < 0 || i >= m || j < 0 || j >= n || last > heights[i][j] || ocean[i][j]) return;

        ocean[i][j] = true;
        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(auto dir : dirs)
            dfs(i + dir.first, j + dir.second, ocean, heights, heights[i][j]);
    }     
};
/*
p   p   p   p   p
p   *   *   *   a
p   *   *   *   a
p   *   *   *   a
a   a   a   a   a
*/