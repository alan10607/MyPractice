//Shortest Path Dijkstra Algorithm O(E + ElogV) O(V + E), V = n, E = times.length
//收集edges需要O(E) O(E), 跑pq while要O(ElogV) O(V)
class Solution743 {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        unordered_map<int, vector<pair<int, int>>> edges; // <起點, <時間, 目的地>>
        for (vector<int> time : times) {
            edges[time[0]].push_back({time[2], time[1]});
        }

        int res = -1;
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; //<時間, 目的地>, 依照時間排序
        pq.push({0, k}); // 放入起點
        unordered_set<int> visited;
        while (!pq.empty()) {
            auto p = pq.top(); pq.pop();
            int time = p.first;
            int node = p.second;
            if (visited.count(node)) continue; // 跳過已到達的

            visited.insert(node);
            res = max(res, time);

            for (pair<int, int> edge : edges[node]) {
                pq.push({time + edge.first, edge.second}); // 記得要累積時間
            }
        }

        return visited.size() == n ? res : -1;
    }
};


//Shortest Path Bellman-Ford Algorithm O(VE) O(V), E = times.size(), V = n
class Solution743_2 {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<int> dists(n + 1, INT_MAX); // 範圍[1,n], index0不使用, 預設INT_MAX表示未到達
        dists[k] = 0;
        for (int i = 0; i < n - 1; ++i) { // Relaxation V-1次
            for (vector<int> time : times) {
                int a = time[0], b = time[1], t = time[2]; // 如果有限制收縮次數, 要建立temp避免污染, 本題不用
                if (dists[a] != INT_MAX) {
                    dists[b] = min(dists[b], dists[a] + t); // 更新為較小的
                }
            }
        }
        // 如果需要檢驗負環則在這裡, 再循環一次看是否能減少距離 (本題不用)
        // for (vector<int> time : times) {
        //     int a = time[0], b = time[1], t = time[2];
        //     if (dists[a] != INT_MAX && dists[b] > dists[a] + t) {
        //         //存在負環
        //     }
        // }

        int res = 0;
        for (int i = 1; i < dists.size(); ++i) { // 去掉index 0
            res = max(res, dists[i]);
        }
        return res == INT_MAX ? -1 : res;
    }
};


//Shortest Path Bellman-Ford Algorithm (SPFA Algorithm) O(VE) O(V + E), E = times.size(), V = n, 進化版的Bellman-Ford
class Solution743_3 {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        unordered_map<int, vector<pair<int, int>>> edges; // <起點, <<目的地, 時間>, ...>>
        for (vector<int> time : times) { // 轉為map方便操作
            edges[time[0]].push_back({time[1], time[2]});
        }

        vector<int> dists(n + 1, INT_MAX); // 範圍[1,n], index0不使用, 預設INT_MAX表示未到達
        dists[k] = 0;
        queue<int> q; // 用來放之後要跑的點
        q.push(k);
        vector<bool> inQueue(n + 1, false); // 該點是否已經在queue裡面
        inQueue[k] = true;
        while (!q.empty()) {
            int a = q.front(); q.pop();
            inQueue[a] = false; // 離開queue

            if (dists[a] == INT_MAX) continue; // 還無法到達

            for (pair<int, int> edge : edges[a]) {
                int b = edge.first, time = edge.second;
                if (dists[a] + time < dists[b]) {
                    dists[b] = dists[a] + time;

                    if (inQueue[b]) continue; // 不用再重新加入queue

                    q.push(b); // 放入下次更新
                    inQueue[b] = true; // 已放入queue
                }
            }
        }

        int res = 0;
        for (int i = 1; i < dists.size(); ++i) { // 去掉index 0
            res = max(res, dists[i]);
        }
        return res == INT_MAX ? -1 : res;
    }
};
/*
最短路徑樹 Shortest-path tree
-   Dijkstra Algorithm      Greedy  從起點開始找能夠到達的最近距離(pq), 不可用負環
-   Bellman-Ford Algorithm  DP      跑V次收縮dist[], 預設不可到達為INT_MAX, 結束再收縮可以檢測負環

最小生成樹 Minimum spanning tree
-   Prim Algorithm          Greedy  任意起點, 選擇連距離這個union最近的點
-   Kruskal Algorithm       Sort    排序所有邊長, 從最小開始連


| Algorithm        |                              Time |      Space | 負權重 edge | 負環     |
| ---------------- | --------------------------------: | ---------: | ---------- | ------- |
| **Dijkstra**     |                      `O(E log V)` | `O(V + E)` |  不行       | 不可     |
| **Bellman-Ford** |                           `O(VE)` |     `O(V)` |  可以       | 可檢測   |
| **SPFA**         | Average 通常 `O(E)`，Worst `O(VE)` | `O(V + E)` |  可以       | 可檢測   |

*/