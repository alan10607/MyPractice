//Backtracking O(mn) O(mn), m = grid.size(), n = grid[0].size();
class Solution1020 {
public:
    int numEnclaves(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i) {
            dfs(i, 0, grid);
            dfs(i, n - 1, grid);
        }
        for (int j = 0; j < n; ++j) {
            dfs(0, j, grid);
            dfs(m - 1, j, grid);
        }

        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    ++res;
                }
            }
        }
        return res;
    }

    void dfs(int i, int j, vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return;

        grid[i][j] = 0;
        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (auto dir : dirs) {
            dfs(i + dir.first, j + dir.second, grid);
        }
    }
};