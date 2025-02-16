//Backtracking O(mn) O(mn)
class Solution695 {
public:
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size(), res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    res = max(res, dfs(i, j, grid));
                }
            }
        }
        return res;
    }

    int dfs(int i, int j, vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) return 0;

        grid[i][j] = 2; // visited
        int area = 1;
        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (auto dir : dirs) {
            area += dfs(i + dir.first, j + dir.second, grid);
        }
        return area;
    }
};