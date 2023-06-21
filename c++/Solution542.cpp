//BFS O(mn) O(mn)
class Solution542 {
public:
    vector<vector<int>> updateMatrix(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        queue<pair<int, int>> q;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(mat[i][j] == 0){
                    q.push({i, j});
                }else{
                    mat[i][j] = -1;//表示要更新
                }
            }
        }

        //BFS只需要遍歷一次, 因為再次遍歷後的數字一定會較大, 並不會再次覆蓋
        while(!q.empty()){
            pair<int, int> p = q.front(); q.pop();

            for(auto dir : dirs){
                int x = p.first + dir.first;
                int y = p.second + dir.second;
                if(x < 0 || x >= m || y < 0 | y >= n || mat[x][y] != -1)
                    continue;

                mat[x][y] = mat[p.first][p.second] + 1;
                q.push({x, y});//相當於java的offer
            }
        }

        return mat;
    }
};