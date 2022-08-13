package leetCode.java;

//LinkedList O(n) O(1)
class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode start = dummy;//整組前一個
        while(start != null){
            ListNode kth = start;
            for(int i=0; i<k; i++){
                kth = kth.next;
                if(kth == null) return dummy.next;
            }

            ListNode cur = start.next;
            ListNode pre = kth.next;
            ListNode end = kth.next;//必須一個新增指定, 否則kth.next會改變位置
            while(cur != end){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            ListNode nextStart = start.next;//剛好會是最後一個
            start.next = pre;
            start = nextStart;
        }
        return dummy.next;
    }
}
/* k = 2
-1 -> 1 -> 2 -> 3 -> 4 -> 5 -> N
 ^s   ^cur      ^pre

      |---------v
-1 -> 1    2 -> 3 -> 4 -> 5 -> N
 ^s   ^pre ^cur

      |---------v
-1 -> 1 <- 2    3 -> 4 -> 5 -> N
 ^s        ^pre ^cur             停止內部while

      |---------v
-1    1 <- 2    3 -> 4 -> 5 -> N
 |---------^
 ^s

      |---------v
-1    1 <- 2    3 -> 4 -> 5 -> N
 |---------^
      ^s

...
*/