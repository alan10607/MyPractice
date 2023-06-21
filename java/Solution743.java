package leetCode.java;

import java.util.*;

//Shortest Path Dijkstra Algorithm O(E + VlogV) O(E + V), E = times.length, V = n
//收集edges需要O(E) O(E), 跑pq while要O(VlogV) O(V)
class Solution743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> edges = new HashMap<>();//<A, <[B, 距離], ...>>
        for(int[] time : times){
            if(!edges.containsKey(time[0]))
                edges.put(time[0], new ArrayList<int[]>());

            edges.get(time[0]).add(new int[]{time[1], time[2]});
        }

        //透過heap獲得離k點的最短距離
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] - b[1]);//<其它點, 距離>
        heap.offer(new int[]{k, 0});
        int res = 0;
        while(!heap.isEmpty()){
            int[] root = heap.poll();
            if(visited.contains(root[0]))
                continue;//已經走過

            visited.add(root[0]);
            res = Math.max(res, root[1]);

            if(edges.containsKey(root[0])){
                List<int[]> children = edges.get(root[0]);
                for(int[] child : children)
                    heap.offer(new int[]{child[0], child[1] + root[1]});//更新距離
            }
        }

        return visited.size() == n ? res : -1;
    }
}