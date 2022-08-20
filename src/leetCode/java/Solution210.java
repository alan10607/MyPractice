package leetCode.java;

import java.util.*;

//Topological Sort O(V + E) O(V + E), V = numCourses, E = prerequisites.length
class Solution210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //1 edges & counts
        int[] counts = new int[numCourses];
        Map<Integer, List<Integer>> edges = new HashMap<>();//<先修課程,<後修課程1, ...>>
        for(int[] pre : prerequisites){
            if(!edges.containsKey(pre[1]))
                edges.put(pre[1], new ArrayList<Integer>());

            edges.get(pre[1]).add(pre[0]);
            counts[pre[0]]++;
        }

        //2 find start
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<counts.length; i++){
            if(counts[i] == 0)
                queue.offer(i);
        }

        //3 run
        List<Integer> visited = new ArrayList<>();//用來檢查是否都以遍歷
        while(!queue.isEmpty()){
            int root = queue.poll();
            visited.add(root);

            if(edges.containsKey(root)){
                for(int child : edges.get(root)){
                    counts[child]--;
                    if(counts[child] == 0)
                        queue.offer(child);
                }
            }
        }

        //return an empty array if it is impossible
        if(visited.size() != numCourses) return new int[]{};

        int[] res = new int[numCourses];
        for(int i=0; i<visited.size(); i++)
            res[i] = visited.get(i);

        return res;
    }
}
/* numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
3 <- 1 <- 0
^--- 2 <--|
*/