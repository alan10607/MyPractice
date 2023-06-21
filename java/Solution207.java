package leetCode.java;

import java.util.*;

//Topological Sort O(V + E) O(V + E)
class Solution207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //1 edges & counts
        int[] counts = new int[numCourses];
        Map<Integer, List<Integer>> edges = new HashMap<>();//<先修課程, <後修課程, ...>>
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
        int visitedCount = 0;
        while(!queue.isEmpty()){
            int root = queue.poll();
            visitedCount++;

            if(edges.containsKey(root)){
                List<Integer> children = edges.get(root);
                for(int child : children){
                    counts[child]--;
                    if(counts[child] == 0)
                        queue.offer(child);
                }
            }
        }

        return visitedCount == numCourses;//判斷是否全部遍歷
    }
}