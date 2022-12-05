package leetCode.java;

//LinkedList O(n) O(1)
class Solution328 {
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return head;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}