//BFS O(mn) O(mn)
class Solution1730 {
public:
    int getFood(vector<vector<char>>& grid) {
        queue<pair<int, int>> q;
        int m = grid.size(), n = grid[0].size();
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(grid[i][j] == '*'){
                    q.push({i, j});
                    grid[i][j] = 'X';
                    break;
                }
            }
        }

        //'*'=start,'#'=end,'X'=block,'O'=empty
        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int res = 0;
        while(!q.empty()){
            ++res;
            for(int k=q.size(); k>0; --k){
                auto it = q.front(); q.pop();
                for(auto dir : dirs){
                    int i = it.first + dir.first;
                    int j = it.second + dir.second;
                    if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 'X') continue;
                    if(grid[i][j] == '#') return res;

                    grid[i][j] = 'X';//'X'代替visited
                    q.push({i, j});
                }
            }
        }

        return -1;
    }
};