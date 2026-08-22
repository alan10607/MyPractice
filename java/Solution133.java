package leetCode.java;

import java.util.*;

//DFS O(V) O(V)
class Solution133 { // 透過DFS + 新舊map 解法
    Map<Node, Node> oldToNew = new HashMap<>(); // <舊 node, 新 node>

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null; // 已經有就回傳那個clone
        }
        if (oldToNew.containsKey(node)) {
            return oldToNew.get(node);
        }
        
        Node clone = new Node(node.val);
        oldToNew.put(node, clone);

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }
}


//BFS O(V) O(V)
class Solution133_2 {
    public Node cloneGraph(Node node) { // 透過BFS + 新舊map 解法
        if (node == null) {
            return null;
        }
        
        Map<Node, Node> oldToNew = new HashMap<>(); // <舊 node, 新 node>
        Queue<Node> q = new ArrayDeque<>();
        q.offer(node);
        oldToNew.put(node, new Node(node.val));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (Node neighbor : cur.neighbors) {
                if (!oldToNew.containsKey(neighbor)) { // 第一次到這個node
                    q.offer(neighbor);
                    oldToNew.put(neighbor, new Node(neighbor.val));
                }
                oldToNew.get(cur).neighbors.add(oldToNew.get(neighbor));
            }
        }
        return oldToNew.get(node);
    }
}