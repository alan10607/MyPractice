package leetCode.java;

import java.util.*;

//LinkedList O(n) O(n)
class Solution138 {
    public Node copyRandomList(Node head) {
        //先走訪記錄一次再串接
        Map<Node, Node> nodeMap = new HashMap<>();//<舊Node, 新Node>
        Node cur = head;
        while(cur != null){
            nodeMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        //開始串接
        cur = head;
        while(cur != null){
            Node clone = nodeMap.get(cur);
            clone.next = nodeMap.get(cur.next);
            clone.random = nodeMap.get(cur.random);//HashMap可以接受null, 這裡直接帶入即可
            cur = cur.next;
        }

        return nodeMap.get(head);
    }
}