package leetCode.java;

//LinkedList O(n) O(1)
class Solution24 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}
/*
-1 -> 1 -> 2 -> 3 -> 4
^p         ^c

-1 -> 1    2 -> 3 -> 4
^p    |    ^c   ^
      -----------

-1 -> 1 <- 2    3 -> 4
^p    |    ^c   ^
      -----------

 ----------v
-1    1 <- 2    3 -> 4
^p    |    ^c   ^
      -----------

 ----------v
-1    1 <- 2    3 -> 4
      ----------^
      ^p             ^c
...

*/