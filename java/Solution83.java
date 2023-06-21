package leetCode.java;

//LinkedList O(n) O(1)
class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;

        ListNode cur = head;
        while(cur.next != null){
            if(cur.val == cur.next.val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        return head;
    }
}