//BFS Shortest Path Dijkstra Algorithm O(n^2 * logn) O(n^2)
class Solution778 {
public:
    int swimInWater(vector<vector<int>>& grid) {
        auto comp = [&grid](pair<int, int>& a, pair<int, int>& b) { // 記得要設lambda capture
            return grid[a.first][a.second] > grid[b.first][b.second];
        };
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(comp)> pq(comp); //<i, j>, grid[i][j]從小到大排列
        pq.push({0, 0});

        int n = grid.size(), res = 0; // 題目給n*n的矩陣
        vector<vector<bool>> visited(n, vector<bool>(n));
        visited[0][0] = true;
        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!pq.empty()) { // BFS with pq
            auto node = pq.top(); pq.pop(); // 先取高度較小的
            res = max(res, grid[node.first][node.second]);

            if (node.first == n - 1 && node.second == n - 1) {
                return res;
            }

            for (auto dir : dirs) {
                int i = node.first + dir.first, j = node.second + dir.second;
                if (i < 0 || i >= n || j < 0 || j >= n || visited[i][j]) continue;

                visited[i][j] = true;
                pq.push({i, j});
            }
        }
        return res;
    }
};


//BFS Shortest Path Dijkstra Algorithm O(n^2 * logn) O(n^2)
class Solution778_2 {
public:
    int swimInWater(vector<vector<int>>& grid) {
        int n = grid.size();
        int res = grid[0][0];
        vector<vector<bool>> visited(n, vector<bool>(n));
        priority_queue<vector<int>, vector<vector<int>>, greater<vector<int>>> pq; // <height, x, y>, 高度小排到大
        pq.push({grid[0][0], 0, 0});
        while (!pq.empty()) {
            vector<int> v = pq.top();
            pq.pop();
            int height = v[0], x = v[1], y = v[2];,

            visited[x][y] = true;
            res = max(res, height);
            if (x == n - 1 && y == n - 1) {
                return res;
            }

            vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (auto dir : dirs) {
                int a = x + dir.first;
                int b = y + dir.second;
                if (a < 0 || a >= n | b < 0 || b >= n || visited[a][b]) continue;
                pq.push({grid[a][b], a, b});
            }
        }

        return res;
    }
};