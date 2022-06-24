package leetCode.javaCode;

import java.util.*;

/**
 *  Linked List
 */
public class NeetCode150_LinkedList {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //Time Complexity: O(n), Space Complexity: O(n)
    //Iteration + HashMap
    class Solution138 {
        public Node copyRandomList(Node head) {
            //先走訪一次生成新的node記錄到map, 再串接
            Map<Node, Node> oldToCopy = new HashMap<>();

            Node cur = head;
            while(cur != null){
                oldToCopy.put(cur, new Node(cur.val));
                cur = cur.next;
            }

            cur = head;
            while(cur != null){
                Node copy = oldToCopy.get(cur);
                copy.next = oldToCopy.get(cur.next);
                copy.random = oldToCopy.get(cur.random);
                cur = cur.next;
            }
            return oldToCopy.get(head);
        }
    }

    //Time Complexity: O(max(m, n)), Space Complexity: O(1)
    //Iteration
    class Solution2 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            int remain = 0;
            while(l1 != null || l2 != null){
                int val1 = (l1 == null ? 0 : l1.val);
                int val2 = (l2 == null ? 0 : l2.val);
                int sum = l1.val + l2.val + remain;
                remain = sum / 10;

                tail.next = new ListNode(sum % 10);
                tail = tail.next;
                if(l1 != null) l1 = l1.next;
                if(l2 != null) l2 = l2.next;
            }

            if(remain > 0)
                tail.next = new ListNode(remain);

            return dummy.next;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    //Floyd Cycle Detection Algorithm
    class Solution287 {
        public int findDuplicate(int[] nums) {
            //solve the problem without modifying the array nums and uses only constant extra space
            //Floyd Algorithm判斷有無環
            int fast = 0;
            int slow = 0;
            do{
                fast = nums[nums[fast]];
                slow = nums[slow];
            }while(fast != slow);

            int start = 0;
            while(start != slow){
                start = nums[start];
                slow = nums[slow];
            }
            return slow;
        }
        /*
        nums = [1, 2, 3, 4, 1]

        0 -> 1 -> 2 -> 3
             ^		   |
             |--- 4 <---

        f: 0 2 4 2 4
        s: 0 1 2 3 4

        1: 0 1
        2: 4 1

        return 1
        */
    }

    //Time Complexity: 初始化, put(), get(): O(1), Space Complexity: O(c), c = capacity
    //Deque
    class Solution146 {
        class LRUCache {
            class Node {
                public int k;
                public int v;
                public Node prev;
                public Node next;
                public Node(int key, int value) {
                    k = key;
                    v = value;
                }
            }

            public Node lru;//Least Recently Used
            public Node mru;//Most Recently Used
            public Map<Integer, Node> cache = new HashMap<>();
            public int max;

            public LRUCache(int capacity) {
                max = capacity;

                //初始化lru, mru
                lru = new Node(0, 0);
                mru = new Node(0, 0);
                lru.next = mru;
                mru.prev = lru;
            }

            public int get(int key) {
                if (cache.containsKey(key)) {
                    remove(cache.get(key));//先從鏈表移除
                    insert(cache.get(key));//再移回最上層
                    return cache.get(key).v;
                }

                return -1;
            }

            public void put(int key, int value) {
                if (cache.containsKey(key))
                    remove(cache.get(key));//從鏈表移除

                //新增node
                Node node = new Node(key, value);
                cache.put(key, node);
                insert(node);

                //判斷大小
                if (cache.size() > max) {
                    Node last = lru.next;
                    remove(last);
                    cache.remove(last.k);
                }
            }

            private void remove(Node node) {
                Node p = node.prev;
                Node n = node.next;
                p.next = n;
                n.prev = p;
            }

            private void insert(Node node) {//放到mru左邊
                Node p = mru.prev;
                Node n = mru;
                p.next = node;
                node.next = n;
                n.prev = node;
                node.prev = p;
            }
            /*
            lru <-> node1 <-> node2 <-> node3 <-> mru
                                 insert()^
            */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    //Iteration
    class Solution25 {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(-1, head);//會改變起點, 必須要有dummy
            ListNode start = dummy;
            while(start != null){
                //find kth
                ListNode kth = start;
                int count = 0;
                while(kth != null && count < k){
                    kth = kth.next;
                    count++;
                }
                if(kth == null) break;

                //reverse group
                ListNode cur = start.next;
                ListNode pre = kth.next;//先設為下一組的開頭
                ListNode end = kth.next;//用來判斷是否到底, 不可變
                while(cur != end){
                    ListNode next = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = next;
                }

                ListNode nextStart = start.next;//剛好會是下一個start
                start.next = kth;//連上start
                start = nextStart;
            }

            return dummy.next;
        }
        /* k = 2
        dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null

        dummy -> 1 <- 2    3 -> 4 -> 5 -> null
                 |---------^

            |---------v
        dummy    1 <- 2    3 -> 4 -> 5 -> null
                 |---------^
        ...
        */
    }

}