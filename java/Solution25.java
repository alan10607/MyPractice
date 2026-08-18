package leetCode.java;

//LinkedList O(n) O(1)
class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode start = dummy, end = head;
        for (int i = 1; end != null; ++i) {
            end = end.next;
            if (i % k == 0) {
                start = reverse(start, end); // 翻轉(start, end)左開右開, 皆不包含
            }
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode start, ListNode end) {
        ListNode pre = end; // 讓第一個node指向end
        ListNode cur = start.next;
        ListNode nextStart = start.next; // 下次進來的start, 用來回傳
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = pre; // 此時pre是反轉後第一個, start接上

        return nextStart; // 回傳下一個start方便下次計算
    }
}

/* if k=3
-1 -> 1 -> 2 -> 3 -> 4 -> 5
 start               end
      next_start
      cur            pre

      ---------------v
-1 -> 1    2 -> 3 -> 4 -> 5
      pre  cur

      ---------------v
-1 -> 1 <- 2    3 -> 4 -> 5
           pre  cur

      ---------------v
-1 -> 1 <- 2 <- 3    4 -> 5
                pre  cur(此時cur=end, 離開while)

      ---------------v
-1    1 <- 2 <- 3    4 -> 5
 ---------------^
 start          pre

start接回pre, 並回傳next_start作為下次的start

*/


//LinkedList O(n) O(1)
class Solution25_2 {
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