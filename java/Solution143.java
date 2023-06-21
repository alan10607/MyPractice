package leetCode.java;

//LinkedList O(n) O(1), 沒有方法需要額外空間
class Solution143 {
    public void reorderList(ListNode head) {
        //1 split
        ListNode mid = getMid(head);
        ListNode a = head;
        ListNode b = mid.next;
        mid.next = null;

        //2 reverse
        b = reverse(b);

        //3 merge
        merge(a, b);
    }

    public ListNode getMid(ListNode head){
        ListNode dummy = new ListNode(-1, head);//偶數時希望在前一個停下來, 所以加上dummy
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    /*
    -1 -> 1 -> 2 -> 3 -> 4 -> 5 -> null
     ^f&s
    -1 -> 1 -> 2 -> 3 -> 4 -> 5 -> null
          ^    ^
    -1 -> 1 -> 2 -> 3 -> 4 -> 5 -> null
               ^         ^
    -1 -> 1 -> 2 -> 3 -> 4 -> 5 -> null  => 1 2 (3) 4 5
                    ^              ^

    -1 -> 1 -> 2 -> 3 -> 4 -> null
     ^f&s
    -1 -> 1 -> 2 -> 3 -> 4 -> null
          ^    ^
    -1 -> 1 -> 2 -> 3 -> 4 -> null  => 1 (2) 3 4
               ^         ^
    */

    public ListNode reverse(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;//此時cur == null
    }

    public void merge(ListNode a, ListNode b){
        //由getMid()方法可知 b鏈長度 <= a鏈長度
        while(b != null){
            ListNode nextA = a.next;
            a.next = b;
            a = nextA;

            ListNode nextB = b.next;
            b.next = a;
            b = nextB;
        }
    }
}