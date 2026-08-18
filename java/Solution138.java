package leetCode.java;

import java.util.*;

//LinkedList O(n) O(n)
class Solution138 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> oldToNew = new HashMap<>(); // <old node, new node>
        Node cur = head;
        while (cur != null) {
            oldToNew.put(cur, new Node(cur.val)); // 直接clone一份到map
            cur = cur.next;
        }

        cur = head;
        while (cur != null) { // 直接從這裡link
            oldToNew.get(cur).next = oldToNew.get(cur.next);
            oldToNew.get(cur).random = oldToNew.get(cur.random); // HashMap可以接受null, 這裡直接帶入即可
            cur = cur.next;
        }

        return oldToNew.get(head);
    }
}