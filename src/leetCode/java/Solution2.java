package leetCode.java;

//LinkedList O(max(m, n)) O(1), m, n表示兩鏈表長度
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int carry = 0;
        while(l1 != null || l2 != null){
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int sum = val1 + val2 + carry;
            tail.next = new ListNode(sum % 10);
            carry = sum / 10;
            tail = tail.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(carry > 0)//加入進位
            tail.next = new ListNode(carry);

        return dummy.next;
    }
}