//BFS O(mn) O(mn)
class Solution1197 {
public:
    int minKnightMoves(int x, int y) {
        if(x == 0 && y == 0) return 0;

        vector<pair<int, int>> dirs
            = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        queue<pair<int, int>> q;
        q.push({0, 0});
        unordered_set<string> visited;//pair沒有支援放進set, 用string代替
        visited.insert("0,0");
        int res = 0;
        while(!q.empty()){
            ++res;
            for(int k=q.size(); k>0; --k){
                auto knight = q.front(); q.pop();
                for(auto dir : dirs){
                    int i = knight.first + dir.first;
                    int j = knight.second + dir.second;
                    if(i == x && j == y) return res;
                    if(i < 0 || j < 0 || i > x + 2 || j > y + 2 //超過目標+2代表沒機會跳到
                        || visited.count(to_string(i) + "," + to_string(j)))
                        continue;

                    q.push({i, j});
                    visited.insert(to_string(i) + "," + to_string(j));
                }
            }
        }
        return -1;
    }
};