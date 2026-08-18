package leetCode.java;

//LinkedList O(n) O(1), 沒有方法需要額外空間
class Solution143 {
    public void reorderList(ListNode head) {
        // find mid
        ListNode fast = head;
        ListNode slow = head;
        // 查看next與next.next, 這樣奇數時會找到中間靠左, 偶數會找到正中, 方便斷開 
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode cur = slow.next;
        slow.next = null;

        // reverse
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // merge
        // 可能是[12][43] or [123][54], head長度>=cur長度
        // 如果是奇數的情況, 例如[123][54], 最後的3其實在之前就已經斷開
        while (head != null && pre != null) {
            ListNode nextA = head.next;
            head.next = pre;
            head = nextA;
            ListNode nextB = pre.next;
            pre.next = head;
            pre = nextB;
        }
    }
}