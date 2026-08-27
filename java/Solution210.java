package leetCode.java;

import java.util.*;

//Topological Sort O(V + E) O(V + E), V = numCourses, E = prerequisites.length
class Solution210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> edges = new HashMap<>(); // <先修課程, <後修課程1, ...>>
        int[] counts = new int[numCourses]; // 後修課程i需要先完成多少先修
        for (int[] pre : prerequisites) {
            edges.putIfAbsent(pre[1], new ArrayList<>());
            edges.get(pre[1]).add(pre[0]);
            ++counts[pre[0]];
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; ++i) {
            if (counts[i] == 0) {
                q.offer(i);
            }
        }

        List<Integer> visited = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            visited.add(node);
            if (edges.containsKey(node)) {
                for (int next : edges.get(node)) {
                    if (--counts[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }

        if (visited.size() != numCourses) { // return empty array if is impossible
            return new int[]{};
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            res[i] = visited.get(i);
        }
        return res;
    }
}
/* numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
3 <- 1 <- 0
^--- 2 <--|
*/