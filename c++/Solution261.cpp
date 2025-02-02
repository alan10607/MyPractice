//Union-Find O(ElogE) O(V), V = n, E = edges.size(), logE因為每次探索最多可能跑n-1次
class Solution261 {//lintcode178
public:
    bool validTree(int n, vector<vector<int>>& edges) {
        // tree=無成還且 點=邊+1
        if (n != edges.size() + 1) return false;

        vector<int> parent(n, -1);
        for (auto edge : edges) {
            int a = find(edge[0], parent);
            int b = find(edge[1], parent);
            if (a == b) return false;
            parent[a] = b;
        }
        return true;
    }

    int find(int node, vector<int>& parent) {
        if (parent[node] == -1) return node;
        return parent[node] = find(parent[node], parent);
    }
};