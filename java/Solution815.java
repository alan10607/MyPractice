package leetCode.java;

import java.util.*;

//BFS O(mn) O(mn), m = routes.size(), n = routes[0].size();
class Solution815 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;

        Map<Integer, Set<Integer>> edges = new HashMap<>();//<車站, <路線1, 路線2, ...>>
        for(int i=0; i<routes.length; i++){
            for(int station : routes[i]){
                if(!edges.containsKey(station)) edges.put(station, new HashSet<>());
                edges.get(station).add(i);
            }
        }

        int res = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(source);
        Set<Integer> visited = new HashSet<>();//visited路線
        while(!q.isEmpty()){
            res++;
            for(int i=q.size(); i>0; i--){
                int node = q.poll();
                for(int bus : edges.get(node)){
                    if(visited.contains(bus)) continue;
                    visited.add(bus);
                    for(int child : routes[bus]){
                        if(child == target) return res;
                        q.offer(child);
                    }
                }
            }
        }

        return -1;
    }
}