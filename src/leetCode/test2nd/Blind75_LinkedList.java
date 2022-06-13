package leetCode.test2nd;

import java.util.*;

public class Blind75_LinkedList {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //*Iteration
    class Solution206 {
        public ListNode reverseList(ListNode head) {
            ListNode cur = head;
            ListNode pre = null;
            while(cur != null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;//cur已經移到null, 回傳pre
        }
    }

    //*Fast slow pointer
    public class Solution141 {
        public boolean hasCycle(ListNode head) {
            if(head == null || head.next == null) return false;

            //起點設一樣就好
            ListNode fast = head;
            ListNode slow = head;
            while(fast != null && fast.next != null){
                fast = fast.next.next;
                slow = slow.next;
                if(slow == fast)
                    return true;

            }
            return false;
        }
    }

    //*Iteration
    class Solution21 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode();
            ListNode tail = dummy;

            while(list1 != null && list2 != null){
                if(list1.val < list2.val){
                    tail.next = list1;
                    list1 = list1.next;
                }else{
                    tail.next = list2;
                    list2 = list2.next;
                }
                tail = tail.next;
            }

            tail.next = list1 == null ? list2 : list1;

            return dummy.next;
        }
    }

    //*Iteration + Merge
    class Solution23 {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists.length == 0) return null;
            if(lists.length == 1) return lists[0];

            return split(lists, 0, lists.length - 1);
        }

        public ListNode split(ListNode[] lists, int start, int end){
            if(start == end) return lists[start];

            int mid = (end + start) / 2;//注意是頭加尾除二
            ListNode left = split(lists, start, mid);
            ListNode right = split(lists, mid + 1, end);

            return merge(left, right);
        }

        public ListNode merge(ListNode a, ListNode b){
            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            while(a != null && b != null){
                if(a.val < b.val){
                    tail.next = a;
                    a = a.next;
                }else{
                    tail.next = b;
                    b = b.next;
                }
                tail = tail.next;
            }

            tail.next = a == null ? b : a;

            return dummy.next;
        }
    }

    //*Iteration
    class Solution19 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode fast = head;
            ListNode dummy = new ListNode(-1, head);//多一個dummy剛好可以到要刪去的前一個的位子
            ListNode slow = dummy;

            for(int i=0; i<n; i++)
                fast = fast.next;

            while(fast != null){
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;

            return dummy.next;
        }
        /*
        x   1   2   3   4   5   x
            ^f0     ^f1         ^f2
        ^s1         ^s2
        */
    }

    //*Iteration
    class Solution143 {
        public void reorderList(ListNode head) {
            if(head.next == null) return;
            //1 中分
            ListNode mid = findMid(head);//獲得中點前一個位置
            ListNode a = head;
            ListNode b = mid.next;
            mid.next = null;

            //2 reverse
            b = reverse(b);

            //3 merge
            merge(a, b);
        }

        public ListNode findMid(ListNode head){
            ListNode fast = head;
            ListNode slow = head;
            while(fast.next != null && fast.next.next != null){
                fast = fast.next.next;
                slow = slow.next;
            }

            return slow;
            //[1, 2, 3, 4] => [2]
            //[1, 2, 3] => [2]
        }

        public ListNode reverse(ListNode head){
            ListNode cur = head;
            ListNode pre = null;
            while(cur != null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }

        public ListNode merge(ListNode a, ListNode b){
            //b會比較短
            while(b != null){
                ListNode nextA = a.next;
                a.next = b;
                a = nextA;

                ListNode nextB = b.next;
                b.next = a;
                b = nextB;
            }
            return a;
        }
    }

}