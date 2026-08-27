package leetCode.java;

import java.util.*;

//Hierholzer's Algorithm, Eulerian Path O(ElogE) O(E), 時間複雜度為演算法本身O(E)乘上PriorityQueue所需O(logE)
class Solution332 {
    List<String> res = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        //Hierholzer Algorithm: 一路走到沒有edge 可以走, 再把路徑從後面組回來, 所以dfs時是透過postorder, 給答案時要reverse
        //return the itinerary lexical order, 透過pq, 文字從小到大
        Map<String, PriorityQueue<String>> edges = new HashMap<>(); // <from, <to1, ...按照字母排列>>
        for (List<String> ticket : tickets) {
            edges.putIfAbsent(ticket.get(0), new PriorityQueue()); // 預設是String依序按照字母大小
            edges.get(ticket.get(0)).offer(ticket.get(1));
        }

        dfs("JFK", edges);
        Collections.reverse(res); // 回傳前要reverse post-order
        return res;
    }

    public void dfs(String cur, Map<String, PriorityQueue<String>> edges) {
        while (edges.containsKey(cur)) { // 回到此層後若pq還有會繼續while, 遍歷所有可能
            PriorityQueue<String> pq = edges.get(cur);
            if (pq.isEmpty()) {
                break;
            }
            String next = pq.poll();
            dfs(next, edges);
        }

        res.add(cur); // 在這裡加入, post-order
    }
}
/*
A -> B --> C -> D
	 ^
     |
	 v
 	 E

     BE互通

初次走到D後回溯並加入 => res=[D,C,B]
回到B繼續DFS, 加入 => res=[D,C,B,E,B]
回到A => res=[D,C,B,E,B,A]

reverse => res=[A,B,E,B,C,D]
*/