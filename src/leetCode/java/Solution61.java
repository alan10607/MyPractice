package leetCode.java;

//Greedy O(n) O(1)
class Solution61 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;

        ListNode cur = head;
        int len = 1;
        while(cur.next != null){
            len++;
            cur = cur.next;
        }
        cur.next = head;

        int skip = len - (k % len) - 1;
        cur = head;
        for(int i=0; i<skip; i++)
            cur = cur.next;

        ListNode newHead = cur.next;
        cur.next = null;
        return newHead;
    }
}