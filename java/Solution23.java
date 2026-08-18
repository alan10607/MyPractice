package leetCode.java;

//LinkedList Merge Sort O(nklogk) O(logk), k = lists.length, n為鏈表長度, klogk相當於合併排序之時間複雜度
class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        // lists[i] is sorted in ascending order
        if (lists.length == 0) {
            return null;
        }
        return split(lists, 0, lists.length - 1);
    }

    public ListNode split(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;
        ListNode a = split(lists, start, mid);
        ListNode b = split(lists, mid + 1, end);
        return merge(a, b);
    }

    public ListNode merge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (a != null && b != null) {
            if (a.val < b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        tail.next = (a != null) ? a : b;
        return dummy.next;
    }
}
/*
                    lists[0~3]
                   /          \
             lists[0~1]      lists[2~3]
              /      \        /      \
          lists[0] lists[1] lists[2] lists[3]

              ↓       ↓        ↓       ↓

                merge            merge
                  ↓              ↓

              merge(0,1)       merge(2,3)
                    \         /
                      merge
*/