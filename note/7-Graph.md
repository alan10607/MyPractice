# Graph
- https://leetcode.com/problems/clone-graph/

- Graph:
    - V = Vertices: 頂點, 節點 (nodes)
    - E = Edges: 邊, 連線
- 依照圖分類
    - 有向圖 (Directed Graph)
        - Topological Sort: Kahn
        - Shortest Path Tree: Dijkstra, Bellman-Ford, SPFA
        - Eulerian Path: Hierholzer
    - 無向圖 (Undirected Graph)
        - Union-Find
        - Bipartition
        - Minimum Spanning Tree: Prim, Kruskal


## Topological Sort 拓撲排序
- https://leetcode.com/problems/course-schedule/
- https://leetcode.com/problems/course-schedule-ii/
- https://leetcode.com/problems/alien-dictionary/
用來處理有向圖 (Directed Graph), 根據 dependency / prerequisite 關係決定 node 的處理順序
步驟:
1. 建立edges map = {from: [to1, to2]}, counts array
2. q=[起點] 放入起點(counts 為0的node), while q
```cpp
bool topologicalSort(int n, vector<vector<int>>& relations) {
    // Kahn's Algorithm
    // n=V的數量, node 的值範圍為[0,n)
    // relations[i] = [from, to], 代表 from -> to, 必須先完成 from, 才能處理 to

    // 1. 建立 adjacency list (方向關係), 計算 indegree (有幾條 edge 指向這個 node)
    unordered_map<int, vector<int>> edges; // <from, <to1, to2...>>
    // 依題目也可以 vector<vector<int>> edges(n);
    vector<int> counts(n); // counts[i]代表i還需要完成多少node才能走
    
    for (const auto& relation : relations) {
        int from = relation[0];
        int to = relation[1];
        edges[from].push_back(to);
        ++counts[to];
    }

    // 2. 找出所有起點
    queue<int> q;
    for (int i = 0; i < counts.size(); ++i) {
        if (counts[i] == 0) { // 找出所有起點
            q.push(i);
        }
    }

    // 3. BFS Topological Sort
    int visited_cnt = 0;
    while (!q.empty()) {
        int node = q.front(); q.pop();
        // 在這處理這個node
        ++visited_cnt;

        for (int child : edges[node]) {
            if (--counts[child] == 0) { // 如果可以向前走
                q.push(child);
            }
        }
    }

    return visited_cnt == n; // 代表全部走完
}
```


## Shortest Path Tree 最短路徑樹
- https://leetcode.com/problems/network-delay-time/
- https://leetcode.com/problems/swim-in-rising-water/
- https://leetcode.com/problems/cheapest-flights-within-k-stops/
Shortest Path Tree(有向): 給定起點 src, 找出 src 到其他 node 的最短距離

### Dijkstra Algorithm
- O(E + ElogV) O(V + E)
- Greedy, pq
- 不能有負權重edge
- 從起點開始, 不斷找現在可以到達並最近的下一個點(透過pq), 一直重複直到走完全部
- 步驟:
    1. 建立edges map = {from: [[to1, dist1], [to2, dist2]]}
    2. pq=[[node, dist]], 依照dist排列, 任意起點[起點, 0], while pq
```cpp
int dijkstra(vector<vector<int>>& times, int n, int k) {
    // 1. 建立 adjacency list
    unordered_map<int, vector<pair<int, int>>> edges; // <from, <to, time>>
    for (vector<int> time : times) {
        edges[time[0]].push_back({time[2], time[1]});
    }

    // 2. 透過pq抓下一個最短邊
    int res = -1;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq; //<time, to>, 依照時間小到大排序
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

    return visited.size() == n ? res : -1; // 若不是所有點都visited, 則存在無法到達的點
}
```


### Bellman-Ford Algorithm
- O(VE) O(V)
- DP, for loop
- 可以測是否有負環
- 每次收縮最多只能保證把一條edge的最短距離算出來, 所以要跑V-1次(E=V-1)
- 跑 V - 1 次收縮dist[], 預設不可到達為INT_MAX, 第 V 次還能更新才代表存在從起點可到達的負環
- 步驟:
    1. 建立dists[], 預設為INT_MAX表無法到達, dists[起點]=0
    2. for loop V-1次, for loop 每個edges[from, to, dist], 每次比較是否可以到達i並縮短dists[i]距離
    3. 再次for loop 每個edges若還能縮小dists[i]則有負環
```cpp
int bellmanFord(vector<vector<int>>& times, int n, int k) {
    // 1. 跑V-1次Relaxation, 每次都考慮所有edges有無可能縮小
    vector<int> dists(n + 1, INT_MAX); // 範圍[1,n], index0不使用, 預設INT_MAX表示未到達
    dists[k] = 0;
    for (int i = 0; i < n - 1; ++i) { // Relaxation V-1次
        for (vector<int> time : times) {
            int a = time[0], b = time[1], t = time[2];
            if (dists[a] != INT_MAX) {
                dists[b] = min(dists[b], dists[a] + t); // 更新為較小的
            }
        }
    }

    // 如果需要檢驗負環則在這裡, 再循環一次看是否能減少距離
    for (vector<int> time : times) {
        int a = time[0], b = time[1], t = time[2];
        if (dists[a] != INT_MAX && dists[b] > dists[a] + t) {
            //存在負環
        }
    }

    // 2. 判斷dists
    int res = 0;
    for (int i = 1; i < dists.size(); ++i) { // 去掉index 0
        res = max(res, dists[i]);
    }
    return res == INT_MAX ? -1 : res;
}
```


### SPFA Algorithm (Shortest Path Faster Algorithm)
- O(VE) O(V + E)
- 優化版的Bellman-Ford
- DP, 透過queue收集下一個要跑的點
- 每次收縮從queue拉出下一點, 比起for loop主動判斷選擇, 如果真的比較近才放入queue
- 如果到第 V 次還能更新才代表存在從起點可到達的負環
- 步驟:
    1. 建立edges map = {from: [[to1, dist1], [to2, dist2]]}
    2. 建立dists[], 預設為INT_MAX表無法到達, dists[起點]=0, 
        q=[node], 放入起點, inQueue表是否已在q, inQueue[起點]=true, 
        while q, 並比較是否可以到達i並縮短dists[i]距離
```cpp
int spfa(vector<vector<int>>& times, int n, int k) {
    // 1. 建立 adjacency list
    unordered_map<int, vector<pair<int, int>>> edges; // <起點, <<目的地, 時間>, ...>>
    for (vector<int> time : times) { // 轉為map方便操作
        edges[time[0]].push_back({time[1], time[2]});
    }

    // 2. q放入可以到達的點, 透過adjacency list抓下一個, 同時更新dists
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

    // 3. 判斷dists
    int res = 0;
    for (int i = 1; i < dists.size(); ++i) { // 去掉index 0
        res = max(res, dists[i]);
    }
    return res == INT_MAX ? -1 : res;
}
```

## Eulerian Path 歐拉路徑
- https://leetcode.com/problems/reconstruct-itinerary/
起點與終點可以不同, 但必須走過所有的邊各一次 (一條恰好經過所有邊的路徑)
dfs postorder所有可能, 之後再reverse為原本


## Union-Find 併查集
- https://leetcode.com/problems/redundant-connection/
- https://leetcode.com/problems/satisfiability-of-equality-equations/
- *https://leetcode.com/problems/graph-valid-tree/
- *https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
- https://leetcode.com/problems/accounts-merge/
用來處理無向圖 (Undirected Graph), 兩兩合併node直到最後形成一坨或數坨 (component)
最後可以判斷兩個node是否屬於同一個connected component歸屬
- 步驟:
    1. 建立parent[], 預設為-1表parent是自己本身
    2. for loop edges, 並把edge兩點union, parents[a]=b
```cpp
void unionFind(int n, vector<vector<int>>& edges) {
    // n=V的數量, node 的值範圍為[0,n)
    if (n != edges.size() + 1) {
        ...
        // 無法合成一顆tree, tree的話數量必定是 V=E+1
        // 此時可能union成多個tree or 成環
    }
    vector<int> parent(n, -1); // -1 表示自己就是parent
    for (auto edge : edges) { // 對每個edge進行node合併
        int a = find(edge[0], parent);
        int b = find(edge[1], parent);
        if (a == b) {
            ...
            // 代表有兩個 node 已經在同一個 component
            // 再加入這條 edge 會成環
        } 
        parent[a] = b; // 合併
    }
    // union完成
}

int find(int node, vector<int>& parent) {
    if (parent[node] == -1) return node;
    return parent[node] = find(parent[node], parent); // 往後找parents並更新
}
```


## Bipartition 二分圖
- https://leetcode.com/problems/is-graph-bipartite/
- https://leetcode.com/problems/possible-bipartition/


## Minimum Spanning Tree MST 最小生成樹
- https://leetcode.com/problems/min-cost-to-connect-all-points/
Minimum Spanning Tree(無向):
### Prim Algorithm
- Greedy, pq
- 維護一mst矩陣, 任意起點, 選擇連距離這個union最近的點, 找最近的點可以用pq優化
- 適合直接計算任意兩點距離的圖
- 步驟:
    1. 建立mst[], 預設為INT_MAX表無法到達, 隨機起點mst[0]=0
    2. for loop V次, 每次找到最小mst當作下一個加入的node, 並for loop其他點更新mst
```cpp
int prim(vector<vector<int>>& points) { // <<x座標, y座標>>
    int n = points.size(), res = 0;
    vector<int> mst(n, INT_MAX); // 離start的最短距離, INT_MAX表示未連上
    mst[0] = 0; // 隨機起點
    vector<bool> visited(n);
    for (int i = 0; i < n; ++i) {
        int node = -1;
        for (int j = 0; j < n; ++j) { // 找到未拜訪且最小的mst
            if (visited[j]) continue; // 如果是已經連上群集的點, 就跳過
            if (node == -1 || mst[j] < mst[node]) {
                node = j;
            }
        }
        visited[node] = true;
        res += mst[node];

        for (int j = 0; j < n; ++j) { // 更新mst
            if (visited[j]) continue; // 同樣跳過已連上的
            // 假設是求manhattan distance 
            int distance = abs(points[j][0] - points[node][0]) + abs(points[j][1] - points[node][1]);
            mst[j] = min(mst[j], distance);
        }
    }
    return res;
}
```

### Kruskal Algorithm
- Sort, Union-Find
- 排序所有邊長, 從最小路經開始, 透過Union-Find連成同一union
- 適合edge已知的圖
- 步驟:
    1. 排序所有edges由距離小到大
    2. union find, 建立parent[], 預設為-1表parent是自己本身, for loop edges, union所有edges
```cpp
int kruskal(vector<vector<int>>& edges) { // <<距離, i點, j點>, ...>
    sort(edges.begin(), edges.end()); // 依照距離小排到大, 預設會用edges[0]比較

    //union-find
    vector<int> parents(n, -1); // 照edges順序, 可以偷懶直接用-1
    int res = 0;
    for (vector<int> edge : edges) {
        int a = find(edge[1], parents);
        int b = find(edge[2], parents);
        if (a != b) {
            parents[b] = a;
            res += edge[0]; // 假設回傳距離
        }
    }
    return res;
}

int find(int node, vector<int>& parents) {
    if (parents[node] == -1) return node;
    return parents[node] = find(parents[node], parents);
}
```