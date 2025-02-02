//MST Kruskal's Algorithm Union-Find O(n^2 * logn) O(n^2), Union-Find時間複雜度為O(ElogE), 但此處E=n^2, 建立edges的空間複雜度O(E)要大於parents的O(V)
class Solution1584 {
public:
    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size();
        vector<vector<int>> edges; // <<距離, i點, j點>, ...>
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int distance = abs(points[i][0] - points[j][0]) + abs(points[i][1] - points[j][1]);
                edges.push_back({distance, i, j});
            }
        }

        sort(edges.begin(), edges.end()); // 依照距離小排到大, 預設會用edges[0]比較

        //union-find
        vector<int> parents(n, -1); // 照edges順序, 可以偷懶直接用-1
        int res = 0;
        for (vector<int> edge : edges) {
            int a = find(edge[1], parents);
            int b = find(edge[2], parents);
            if (a != b) {
                parents[b] = a;
                res += edge[0];
            }
        }
        return res;
    }

    int find(int node, vector<int>& parents) {
        if (parents[node] == -1) return node;
        return parents[node] = find(parents[node], parents);
    }
};


//MST Prim's Algorithm O(nlogn) O(n), 這題的prim不用建立edge, 因為任何一個點都存在雙向路線(直接用for loop + continue去除連上的)
class Solution1584_2 {
public:
    int minCostConnectPoints(vector<vector<int>>& points) {
        int n = points.size(), res = 0;
        vector<int> mst(n, -1); // 離start的最短距離, -1表示未連上
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; // <到群集的距離, 某點>, 依照距離小排到大
        pq.push({0, 0}); // 設定0為start, 距離0
        while (!pq.empty()) {
            auto p = pq.top(); pq.pop();
            int dist = p.first;
            int node = p.second;
            if (mst[node] != -1) continue; // 如果是已經連上群集的點, 就跳過

            mst[node] = dist; // 設定最短距離
            res += dist;

            // 尋找群集與其他點的距離
            for (int i = 0; i < n; ++i) {
                if (mst[i] != -1) continue; // 同樣跳過已連上的
                int distance = abs(points[i][0] - points[node][0]) + abs(points[i][1] - points[node][1]);
                pq.push({distance, i});
            }
        }
        return res;
    }
};