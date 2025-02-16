//Backtracking O(mn) O(mn), m = grid.size(), n = grid[0].size();
class Solution1905 {
public:
    int countSubIslands(vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid2.size(), n = grid2[0].size(), res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && isSubIsland(i, j, grid1, grid2)) {
                    ++res;
                }
            }
        }
        return res;
    }

    bool isSubIsland(int i, int j, vector<vector<int>>& grid1, vector<vector<int>>& grid2) {
        int m = grid2.size(), n = grid2[0].size();
        if (i < 0 || i >= m || j < 0 || j >= n || grid2[i][j] != 1) return true;

        bool isSub = grid1[i][j] == 1; // 判斷是否為sub island
        grid2[i][j] = 2; // 2代表visited
        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (auto dir : dirs) {
            if (!isSubIsland(i + dir.first, j + dir.second, grid1, grid2)) {
                isSub = false; // 若有某格不是sub island, 那整塊都不是
            }
        }
        return isSub;
    }
};