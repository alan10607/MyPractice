package leetCode.java;

//Fast & Slow Pointer Floyd Cycle Detection Algorithm O(n) O(1)
class Solution142 {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(true){
            if(fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) break;
        }

        ListNode start = head;
        while(start != slow){
            start = start.next;
            slow = slow.next;
        }
        return start;
    }
}