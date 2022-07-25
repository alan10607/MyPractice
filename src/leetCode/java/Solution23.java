package leetCode.java;

//LinkedList O(nklogk) O(logk), k = lists.length, n為鏈表長度, klogk相當於合併排序之時間複雜度
class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];

        return split(0, lists.length - 1, lists);
    }

    public ListNode split(int start, int end, ListNode[] lists){
        if(start == end) return lists[start];

        int mid = (start + end) / 2;
        ListNode left = split(start, mid, lists);
        ListNode right = split(mid + 1, end, lists);
        return merge(left, right);
    }

    public ListNode merge(ListNode a, ListNode b){
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while(a != null && b != null){
            if(a.val < b.val){
                tail.next = a;
                a = a.next;
            }else{
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        tail.next = a == null ? b : a;

        return dummy.next;
    }
}
