package leetCode.java;

import java.util.*;

//DFS O(V) O(V)
class Solution133 {
    public Map<Node, Node> memo = new HashMap<>();

    public Node cloneGraph(Node node) {
        if(node == null) return null;
        if(memo.containsKey(node)){
            return memo.get(node);//已經有就回傳那個clone
        }

        Node clone = new Node(node.val);
        memo.put(node, clone);

        for(Node next : node.neighbors){
            clone.neighbors.add(cloneGraph(next));
        }

        return clone;
    }
}