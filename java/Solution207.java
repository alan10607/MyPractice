package leetCode.java;

import java.util.*;

//Topological Sort O(V + E) O(V + E)
class Solution207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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

        int visitedCount = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            ++visitedCount;

            if (edges.containsKey(node)) {
                for (int next : edges.get(node)) {
                    if (--counts[next] == 0) {
                        q.offer(next);
                    }
                }
            }
        }

        return visitedCount == numCourses; // 判斷是否全部遍歷
    }
}

//DFS O(V + E) O(V + E)
class Solution207_2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 也可以透過判斷是否成環, 有環就不可能修完課, 使用 DFS Cycle Detection
        List<Integer>[] edges = new ArrayList[numCourses]; // [<後修課程1, 後修課程2, ...>], 使用map或List[]都可以
        for (int i = 0; i < numCourses; ++i) { // 但用List[]要注意初始化
            edges[i] = new ArrayList<>(); // 如果用Arrays.fill(edges,new ArrayList<>());會全部指向同一個list
        }
        for (int[] pre : prerequisites) {
            edges[pre[1]].add(pre[0]);
        }

        int[] states = new int[numCourses]; //0 :未拜訪, 1:已拜訪, 2:已確定沒有成環
        for (int i = 0; i < numCourses; ++i) {
            if (hasCycle(edges, states, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCycle(List<Integer>[] edges, int[] states, int i) {
        if (states[i] == 1) { // 回到目前已經visited的node
            return true;
        }
        if (states[i] == 2) { // 已確認過無環
            return false;
        }
        
        states[i] = 1; // visited
        for (int next : edges[i]) {
            if (hasCycle(edges, states, next)) {
                return true;
            }
        }
        states[i] = 2; // 確認過無環
        return false;
    }
}