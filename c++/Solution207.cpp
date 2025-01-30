//Topological Sort O(V + E) O(V + E)
class Solution207 {
public:
    bool canFinish(int num_courses, vector<vector<int>>& prerequisites) {
        unordered_map<int, vector<int>> edges; // <先修課程, <後修課程1, ...>>
        vector<int> counts(num_courses);
        for (vector<int> pre : prerequisites) {
            edges[pre[1]].push_back(pre[0]);
            ++counts[pre[0]];
        }

        queue<int> q;
        for (int i = 0; i < counts.size(); ++i) {
            if (counts[i] == 0) { // 找出所有先修
                q.push(i);
            }
        }

        int visited_cnt = 0;
        while (!q.empty()) {
            int node = q.front(); q.pop();
            ++visited_cnt;

            for (int child : edges[node]) {
                if (--counts[child] == 0) {
                    q.push(child);
                }
            }
        }

        return visited_cnt == num_courses;
    }
};