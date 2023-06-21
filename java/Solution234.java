package leetCode.java;

//LinkedList O(n) O(1)
class Solution234 {
    public boolean isPalindrome(ListNode head) {
        //find mid
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //reverse second
        ListNode pre = null;
        while(slow != null){
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        //check
        while(pre != null){
            if(head.val != pre.val) return false;
            head = head.next;
            pre = pre.next;
        }

        return true;
    }
}