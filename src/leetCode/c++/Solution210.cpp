//Topological Sort O(V + E) O(V + E), V = numCourses, E = prerequisites.size()
class Solution210 {
public:
    vector<int> findOrder(int numCourses, vector<vector<int>>& prerequisites) {
        unordered_map<int, vector<int>> edges;//<先修課程, <後修課程1, 後修課程2,..>>
        vector<int> cnt(numCourses);
        for(auto pre : prerequisites){
            edges[pre[1]].push_back(pre[0]);
            ++cnt[pre[0]];
        }

        queue<int> q;
        for(int i=0; i<cnt.size(); ++i)
            if(cnt[i] == 0) q.push(i);

        vector<int> res;
        while(!q.empty()){
            for(int i=q.size(); i>0; --i){
                int node = q.front(); q.pop();
                res.push_back(node);
                if(edges.count(node)){
                    for(auto child : edges[node]){
                        if(--cnt[child] == 0)
                            q.push(child);
                    }
                }
            }
        }
        return res.size() == numCourses ? res : vector<int>();//若有成環, return empty array
    }
};