package leetCode.java;

//LinkedList O(n) O(1)
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode dummy = new ListNode(-1, head);
        ListNode slow = dummy;//slow從dummy開始

        //先讓fast走n步
        for(int i=0; i<n ;i++)
            fast = fast.next;

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        //因為slow從dummy開始, 剛好會是要跳過的node的前一個
        slow.next = slow.next.next;

        return dummy.next;//改變鏈表不要直接用head, 避免head是被換掉的那個
    }
}
/* n = 2

先讓fast走n步:
-1 -> 1 -> 2 -> 3 -> N
 ^s             ^f

-1 -> 1 -> 2 -> 3 -> N    此時break while
      ^s             ^f

slow.next = slow.next.next 剛好會跳過倒數第n個
*/