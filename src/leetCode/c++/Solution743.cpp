//Shortest Path Dijkstra Algorithm O(E + VlogV) O(E + V), E = times.size(), V = n
//收集edges需要O(E) O(E), 跑pq while要O(VlogV) O(V)
class Solution743 {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        unordered_map<int, vector<pair<int, int>>> edges;//<起點, <目的地, 時間>>
        for(auto time : times)
            edges[time[0]].push_back({time[1], time[2]});

        auto comp = [](pair<int, int>& a, pair<int, int>& b){
                return a.second > b.second;//時間小到大
            };
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(comp)> pq(comp);//<目的地, 時間>, O(VlogV)
        pq.push({k, 0});
        unordered_set<int> visited;
        int res = 0;
        while(!pq.empty()){
            auto node = pq.top(); pq.pop();//<地點, 時間>
            if(visited.count(node.first)) continue;//在這裡判斷visited, 才可以把順序交給pq

            visited.insert(node.first);
            res = max(res, node.second);

            for(auto edge : edges[node.first])
                pq.push({edge.first, edge.second + node.second});//累積距離
        }

        return visited.size() == n ? res : -1;
    }
};

//Shortest Path Bellman-Ford Algorithm O(VE) O(V), E = times.size(), V = n
class Solution743_2 {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        vector<int> dist(n + 1, INT_MAX);//1<=k<=n, dist[0]不使用
        dist[k] = 0;
        for(int i=1; i<=n; ++i){
            for(auto time: times){//<起點, 目的地, 時間>
                int x = time[0], y = time[1], z = time[2];
                if(dist[x] < INT_MAX && dist[y] > dist[x] + z){//更新為較小的
                    dist[y] = dist[x] + z;
                }
            }
        }

        int res = 0;
        for(int i=1; i<=n; ++i)//如果需要檢驗負環則在這裡做(再循環一次是否能減少距離), 本題不用
            res = max(res, dist[i]);

        return res == INT_MAX ? -1 : res;
    }
};

//Shortest Path Bellman-Ford Algorithm O(E + V) O(V), E = times.size(), V = n, 進化版的Bellman-Ford
class Solution743_3 {
public:
    int networkDelayTime(vector<vector<int>>& times, int n, int k) {
        unordered_map<int, vector<pair<int, int>>> edges;//<起點, <目的地, 時間>>
        for(auto time : times)
            edges[time[0]].push_back({time[1], time[2]});

        vector<int> dist(n + 1, INT_MAX);//1<=k<=n, dist[0]不使用
        dist[k] = 0;
        queue<int> q;//用來放之後要跑的點
        q.push(k);
        while(!q.empty()){
            unordered_set<int> visited;//每次收縮只能跑各個點一次
            int x = q.front(); q.pop();

            for(auto edge : edges[x]){
                int y = edge.first, z = edge.second;
                if(dist[x] < INT_MAX && dist[y] > dist[x] + z){
                    dist[y] = dist[x] + z;

                    if(visited.count(y)) continue;

                    visited.insert(y);
                    q.push(y);//放入下次更新
                }
            }
        }

        int res = 0;
        for(int i=1; i<=n; ++i)
            res = max(res, dist[i]);

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

*/