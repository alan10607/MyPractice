//Shortest Path Bellman–Ford Algorithm O(kE) O(V), E = flights.size(), V = n
class Solution787 {
public:
    int findCheapestPrice(int n, vector<vector<int>>& flights, int src, int dst, int k) {
        vector<int> dist(n, INT_MAX);
        dist[src] = 0;
        for(int i = 0; i < k + 1; ++i){//可轉機k次, 即可走k+1
            vector<int> temp = dist;//記得用temp避免混淆
            for(auto f : flights){
                if(temp[f[0]] != INT_MAX)
                    dist[f[1]] = min(dist[f[1]], temp[f[0]] + f[2]);
            }
        }
        return dist[dst] == INT_MAX ? -1 : dist[dst];
    }
};
/* n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
k       0   1   2   3
0       0
1       0   100
2       0   100 200 700
3       0   100 200 400

*/