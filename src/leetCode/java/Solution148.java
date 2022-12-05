package leetCode.java;

//LinkedList O(nlogn) O(logn)
class Solution148 {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode fast = head;
        ListNode slow = head;
        ListNode preSlow = head;
        while(fast != null && fast.next != null){
            preSlow = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        preSlow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return merge(left, right);
    }

    public ListNode merge(ListNode a, ListNode b){
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(a != null && b!= null){
            if(a.val < b.val){
                cur.next = a;
                a = a.next;
            }else{
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }

        cur.next = a != null ? a : b;
        return dummy.next;
    }
}