//BFS O(V) O(V)
class Solution310 {
public:
    vector<int> findMinHeightTrees(int n, vector<vector<int>>& edges) {
        if(n == 1) return {0};//題目設n>0, n=2時下面q會吃到edges

        //MST的root剛好會是最中心的node
        vector<unordered_set<int>> adjs(n);
        for(auto e : edges){
            adjs[e[0]].insert(e[1]);
            adjs[e[1]].insert(e[0]);
        }

        //找出最外側, 即只有連一條的node
        queue<int> q;
        for(int i=0; i<adjs.size(); ++i){
            if(adjs[i].size() == 1)
                q.push(i);
        }

        //BFS
        int unvisitCnt = n;//未走訪
        while(unvisitCnt > 2){
            unvisitCnt -= q.size();//先記錄走訪
            for(int i=q.size(); i>0; --i){
                int node = q.front(); q.pop();
                for(auto child : adjs[node]){
                    adjs[child].erase(node);//到連接的點消除自己
                    if(adjs[child].size() == 1)//一條邊, 此時是tree
                        q.push(child);
                }
            }
        }

        vector<int> res;
        while(!q.empty()){
            res.push_back(q.front()); q.pop();
        }

        return res;
    }
};
/*
    1
    ^
    |
    v
2<->3<->4<->5

    1
    |
    |
    v
2-->3<->4<--5

消除到剩下1 or 2個:
因為2代表距離外圈一樣遠, 1代表最遠
但不可能有3(一定有一點會更遠)
*/