//Union-Find O(ElogE) O(V), 本題V = E = edges.size()
class Solution684 {
public:
    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        vector<int> parent(edges.size() + 1, -1);//範圍是1~n, 0不使用
        for(auto edge : edges){
            int a = find(edge[0], parent);
            int b = find(edge[1], parent);

            if(a == b) return edge;

            parent[a] = b;
        }
        return {};
    }

    int find(int node, vector<int> parent){
        if(parent[node] == -1) return node;
        return parent[node] = find(parent[node], parent);
    }
};