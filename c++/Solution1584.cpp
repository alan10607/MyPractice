//MST Kruskal's Algorithm O(n^2 * logn) O(n^2), Union-Find時間複雜度為O(ElogE), 但此處E=n^2, 建立edges的空間複雜度O(E)要大於parents的O(V)
class Solution1584 {
public:
    int minCostConnectPoints(vector<vector<int>>& points) {
        vector<vector<int>> edges;//<<距離, A點, B點>, ...>
        for(int i=0; i<points.size(); ++i){
            for(int j=i+1; j<points.size(); ++j){
                int dist = abs(points[i][0] - points[j][0]) + abs(points[i][1] - points[j][1]);
                edges.push_back({dist, i, j});
            }
        }

        auto comp = [](vector<int>& a, vector<int>& b){
                return a[0] < b[0];
            };
        sort(edges.begin(), edges.end(), comp);//依照距離小排到大

        //union-find
        vector<int> parent(points.size(), -1);
        int res = 0, visitedCnt = 1;
        for(auto edge : edges){
            int a = find(edge[1], parent);
            int b = find(edge[2], parent);

            if(a != b){
                res += edge[0];
                parent[a] = b;
                if(++visitedCnt == points.size())
                    break;
            }
        }

        return res;
    }

    int find(int node, vector<int>& parent){
        if(parent[node] == -1) return node;
        return parent[node] = find(parent[node], parent);
    }
};