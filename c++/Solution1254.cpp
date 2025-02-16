//Backtracking O(mn) O(mn), m = grid.size(), n = grid[0].size();
class Solution1254 {
public:
    int closedIsland(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size(), res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 &&
                    isClosed(i, j, grid)) { // isClosed後grid[i][j]就會改成1
                    ++res;
                }
            }
        }
        return res;
    }

    bool isClosed(int i, int j, vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        if (i < 0 || i >= m || j < 0 || j >= n) return false;
        if (grid[i][j] == 1) return true;

        grid[i][j] = 1;

        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        bool res = true;
        for (auto dir : dirs) {
            if (!isClosed(i + dir.first, j + dir.second, grid)) {
                res = false; // &=是錯的, 記得不要搞錯
            }
        }
        return res;
    }
};

