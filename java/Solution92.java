package leetCode.java;

//LinkedList O(n) O(1)
class Solution92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        ListNode start = dummy, end = dummy;
        for(int i=0; i<=right; ++i){
            if(i == left - 1) start = cur;
            if(i == right) end = cur.next;
            cur = cur.next;
        }

        reverse(start, end);//翻轉start到end之間, 但不包含start,end
        return dummy.next;
    }

    public void reverse(ListNode start, ListNode end){
        ListNode pre = end;
        ListNode cur = start.next;
        while(cur != end){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = pre;
    }
}