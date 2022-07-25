package leetCode.java;

//LinkedList O(n) O(1)
class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;//直接設null, 不用建立dummy
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;//此時cur已經null
    }
}
/*
null    1 -> 2 -> null
^p      ^c

null <- 1    2 -> null
        ^p   ^c

null <- 1 <- 2 -> null
             ^p   ^c
*/