//Topological Sort O(V + E) O(V + E), V = numCourses, E = prerequisites.size()
class Solution210 {
public:
    vector<int> findOrder(int num_courses, vector<vector<int>>& prerequisites) {
        unordered_map<int, vector<int>> edges; // <先修課程, <後修課程1, ...>>
        vector<int> counts(num_courses);
        for (vector<int> pre : prerequisites) {
            edges[pre[1]].push_back(pre[0]);
            ++counts[pre[0]];
        }

        queue<int> q;
        for (int i = 0; i < counts.size(); ++i) {
            if (counts[i] == 0) {
                q.push(i);
            }
        }

        vector<int> res;
        while (!q.empty()) {
            int node = q.front(); q.pop();
            res.push_back(node);

            for (int child : edges[node]) {
                if (--counts[child] == 0) {
                    q.push(child);
                }
            }
        }

        return res.size() == num_courses ? res : vector<int>(); // 若有成環, return empty array
    }
};