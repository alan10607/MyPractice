//Union-Find O(ElogE) O(V), 本題V = E = edges.size()
class Solution684 {
public:
    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        vector<int> parents(edges.size() + 1, -1); // 範圍是1~n, 0不使用
        for (vector<int> edge : edges) {
            int a = find(edge[0], parents);
            int b = find(edge[1], parents);

            if (a == b) {
                return edge;
            }

            parents[a] = b;
        }
        return {};
    }

    int find(int node, vector<int>& parents) {
        if (parents[node] == -1) return node;
        return parents[node] = find(parents[node], parents);
    }
};


//DFS O(V + E) O(E), 本題V = E = edges.size()
class Solution684_2 {
public:
    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        unordered_map<int, unordered_set<int>> graph;
        for (vector<int> edge : edges) {
            if (dfs(edge[0], edge[1], -1, graph)) {
                return edge; // 如果在還沒連接前就連得上, 代表這個edge是多餘的
            }
            graph[edge[0]].insert(edge[1]); // 無向圖, 加入雙向
            graph[edge[1]].insert(edge[0]);
        }
        return {};
    }

    bool dfs(int node, int target, int pre, unordered_map<int, unordered_set<int>>& graph) {
        if (graph[node].count(target)) return true; // node與target有連上

        for (int child : graph[node]) {
            if (child == pre) continue; // 不走回頭路
            if (dfs(child, target, node, graph)) {
                return true;
            }
        }
        return false;
    }
};