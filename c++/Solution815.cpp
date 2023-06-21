//BFS O(mn) O(mn), m = routes.size(), n = routes[0].size();
class Solution815 {
public:
    int numBusesToDestination(vector<vector<int>>& routes, int source, int target) {
        if(source == target) return 0;

        unordered_map<int, unordered_set<int>> edges;//<車站, <路線1, 路線2, ...>>
        for(int i=0; i<routes.size(); ++i){
            for(int station : routes[i]){
                edges[station].insert(i);
            }
        }

        queue<int> q;
        q.push(source);
        unordered_set<int> visited;//已拜訪路線
        int res = 0;
        while(!q.empty()){
            ++res;
            for(int i=q.size(); i>0; --i){
                int node = q.front(); q.pop();
                for(int bus : edges[node]){//車站->-路線->車站
                    if(visited.count(bus)) continue;
                    visited.insert(bus);
                    for(int child : routes[bus]){
                        if(child == target) return res;
                        q.push(child);
                    }
                }
            }
        }
        return -1;
    }
};
/* routes = [[1,2,7],[3,6,7]], source = 1, target = 6
edges:
1,[0]
2,[0]
3,[1]
6,[1]
7,[0,1]

1 -> (0) -> 2 -> (0)
         -> 7 -> (0)
              -> (1)
*/