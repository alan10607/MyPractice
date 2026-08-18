package leetCode.java;

//LinkedList O(n) O(1)
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = head; // 提早從head開始, 剛好可以在後面讓slow在目標前一個
        ListNode slow = dummy; // slow從dummy開始
        for (int i = 0; i < n; ++i) { // 先讓fast走n步
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;
    }
}
/*
ex: head = [1,2,3,4,5], n = 2

                        target
                        v
dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null
  s                f
         s              f
              s              f
                   s               f




ex: head = [1,2,3,4,5], n = 5

         target
         v
dummy -> 1 -> 2 -> 3 -> 4 -> 5 -> null
  s                                f


*/


class Solution19_2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < n; ++i) {
            fast = fast.next;
        }

        if (fast == null) { // 代表移除第一個
            return head.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;

        return head;
    }
}
/*
ex: head = [1,2,3,4,5], n = 2

              target
               v
1 -> 2 -> 3 -> 4 -> 5 -> null
s         f
     s         f
          s         f
直接判斷fast.next==null, 讓slow剛好在要刪除之前一個位子上



ex: head = [1,2,3,4,5], n = 5

target
v
1 -> 2 -> 3 -> 4 -> 5 -> null
s                        f
f==null時, 刪除倒數第五個=第一個, 直接回head.next

*/