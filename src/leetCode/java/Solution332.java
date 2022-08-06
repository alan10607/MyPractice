package leetCode.java;

import java.util.*;

//Hierholzer's Algorithm O(ElogE) O(E), 時間複雜度為演算法本身O(E)乘上PriorityQueue所需O(logE)
class Solution332 {
    public List<String> findItinerary(List<List<String>> tickets) {
        //1 edges
        //PriorityQueue因為return the itinerary that has the smallest lexical order
        Map<String, PriorityQueue<String>> edges = new HashMap<>();//<from, <to1, ...>>
        for(List<String> ticket : tickets){
            if(!edges.containsKey(ticket.get(0)))
                edges.put(ticket.get(0), new PriorityQueue<String>());

            edges.get(ticket.get(0)).offer(ticket.get(1));
        }

        //2 DFS
        List<String> res = new ArrayList<>();
        dfs("JFK", edges, res);

        return res;
    }

    public void dfs(String node, Map<String, PriorityQueue<String>> edges, List<String> res){
        while(edges.containsKey(node) && !edges.get(node).isEmpty()){
            String child = edges.get(node).poll();//O(logn)
            dfs(child, edges, res);
        }
        res.add(0, node);//沒有路走了, 開始逆序入棧
    }
}
/*
A -> B --> C -> D
	 ^
     |
	 v
 	 E

初次走到D後回溯並加入 => BCD
回到B繼續DFS, 加入 => BEB BCD
回到A => A BEB BCD
*/