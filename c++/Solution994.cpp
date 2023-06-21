//BFS O(mn) O(mn)
class Solution994 {
public:
    int orangesRotting(vector<vector<int>>& grid) {
        queue<pair<int, int>> q;
        int m = grid.size(), n = grid[0].size(), fresh = 0;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(grid[i][j] == 2){
                    q.push({i, j});
                }else if(grid[i][j] == 1){
                    ++fresh;//用來記錄是否會全爛掉
                }
            }
        }

        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int res = 0;
        while(!q.empty() && fresh > 0){//若全爛掉就不要再迴圈
            for(int k=q.size(); k>0; --k){
                auto p = q.front(); q.pop();
                for(auto dir : dirs){
                    int i = p.first + dir.first;
                    int j = p.second + dir.second;
                    if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1)
                        continue;//進到內層再判斷, 可以減少記憶體

                    grid[i][j] = 2;
                    --fresh;

                    q.push({i, j});
                }
            }
            ++res;
        }

        return fresh == 0 ? res : -1;
    }
};