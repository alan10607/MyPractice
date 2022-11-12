//Topological Sort O(V + E) O(V + E)
class Solution207 {
public:
    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        unordered_map<int, vector<int>> edges;//<先修課程,<後修課程1, ...>, ...>
        vector<int> counts(numCourses, 0);
        for(auto pre : prerequisites){
            edges[pre[1]].push_back(pre[0]);
            ++counts[pre[0]];
        }

        queue<int> q;
        for(int i=0; i<counts.size(); ++i){
            if(counts[i] == 0)//找出所有先修
                q.push(i);
        }

        int visitedCnt = 0;
        while(!q.empty()){
            int node = q.front(); q.pop();
            ++visitedCnt;
            if(edges.count(node)){
                for(int child : edges[node])
                if(--counts[child] == 0)
                    q.push(child);
            }
        }

        return visitedCnt == numCourses;
    }
};