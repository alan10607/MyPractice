//BFS O(mn) O(mn)
class Solution286 {//lintcode663
public:
    void wallsAndGates(vector<vector<int>> &rooms) {
        int m = rooms.size(), n = rooms[0].size();
        queue<pair<int, int>> q;
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(rooms[i][j] == 0)
                    q.push({i, j});
            }
        }

        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int num = 1;
        while(!q.empty()){
            for(int k=q.size(); k>0; --k){
                auto node = q.front(); q.pop();
                for(auto dir : dirs){
                    int i = node.first + dir.first;
                    int j = node.second + dir.second;
                    if(i < 0 || i >= m || j < 0 || j >= n || rooms[i][j] != INT_MAX)
                        continue;

                    rooms[i][j] = num;
                    q.push({i, j});
                }
            }
            ++num;
        }
    }
};