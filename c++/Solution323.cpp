//Union-Find O(ElogE) O(V), V = n, E = edges.size()
class Solution323 {
public:
    int countComponents(int n, vector<pair<int, int>>& edges) {
        vector<int> parent(n, -1);
        int res = n;//union的數量, 接合時-1
        for(auto edge : edges){
            int a = find(edge.first, parent);
            int b = find(edge.second, parent);

            if(a != b) --res;

            parent[a] = b;
        }
        return res;
    }

    int find(int node, vector<int> parent){
        if(parent[node] == -1) return node;
        return parent[node] = find(parent[node], parent);
    }
};