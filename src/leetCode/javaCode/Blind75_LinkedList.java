package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_LinkedList {

    //Time Complexity: O(n), Space Complexity: O(1), n為鏈表長度
    class Solution206 {
        class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        public ListNode reverseList(ListNode head) {
            //直接疊代iterative
            ListNode pre = null;
            ListNode cur = head;

            while(cur != null){
                ListNode next = cur.next;//先把下一個拉出來記著
                cur.next = pre;//改變鏈結
                pre = cur;//滾動指標
                cur = next;//滾動指標
            }

            return pre;

        /*
        null    1 -> 2 -> 3 -> null
        ^pre    ^cur

        null <- 1    2 -> 3 -> null, 記著next = 2
                ^pre ^cur

        null <- 1 <- 2    3 -> null, 記著next = 3
                     ^pre ^cur

        null <- 1 <- 2 <- 3    null, 記著next = null
                          ^pre ^cur
        */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    public class Solution141 {
        class ListNode {
            int val;
            ListNode next;
            ListNode(int x) {
                val = x;
                next = null;
            }
        }

        public boolean hasCycle(ListNode head) {
            //設定例外
            if(head == null || head.next == null)
                return false;

            //透過快慢指標
            ListNode fast = head.next;
            ListNode slow = head;

            //設定跳脫點, 判斷fast就夠但也要判斷fast.next, 否則下面fast.next.next會nullPointerException
            while(fast != null && fast.next != null){
                if(fast == slow)
                    return true;//有相遇代表有環

                fast = fast.next.next;
                slow = slow.next;
            }

            return false;//沒有相遇等於沒有環
        }

        //Time Complexity: O(n), Space Complexity: O(n)
        public boolean hasCycle2(ListNode head) {
            //使用set記憶, 有環時回true
            Set<ListNode> remember = new HashSet<ListNode>();
            ListNode cur = head;

            while(cur != null){
                if(remember.contains(cur))
                    return true;

                remember.add(cur);
                cur = cur.next;
            }

            return false;
        }
    }

    //Time Complexity: O(m + n), Space Complexity: O(1), m, n各為鏈表長度
    class Solution21 {
        class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode();
            ListNode tail = dummy;

            while(list1 != null && list2 != null){
                if(list1.val < list2.val){
                    //要複製整個ListNode而不是只有val, 否則tail = tail.next;會得到一個null
                    tail.next = list1;
                    list1 = list1.next;
                }else{
                    tail.next = list2;
                    list2 = list2.next;
                }
                tail = tail.next;
            }

            //因為已經排序, 把剩下的直接接上右邊去
            tail.next = list1 == null ? list2 : list1;

            return dummy.next;//記得要去掉dummy頭
        }
    }

    //Time Complexity: O(m n logn), Space Complexity: O(n)
    //n = lists.length, m為最大的鏈表長度, n logn就是合併排序的時間複雜度, 另外每次需要疊代m次
    //空間複雜度則在遞迴中需要lists.length個
    class Solution23 {
        class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        public ListNode mergeKLists(ListNode[] lists) {
            //其實就是合併所有鏈表, 自然想到類似merge sort的合併法

            //設定例外條件
            if(lists.length == 0) return null;
            if(lists.length == 1) return lists[0];

            return split(lists, 0, lists.length - 1);
        }

        public ListNode split(ListNode[] lists, int start, int end){
            //設定返回條件, 如果已經不能細分就回傳這個ListNode
            if(start == end) return lists[start];

            //1. spilt
            int len = end - start;//本次範圍的長度
            int mid = start + len / 2;
            ListNode a = split(lists, start, mid);
            ListNode b = split(lists, mid + 1, end);

            return merge(lists, a, b);
        }

        public ListNode merge(ListNode[] lists, ListNode a, ListNode b){
            //2. merge
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

            //加入剩下的
            tail.next = a == null ? b : a;

            return dummy.next;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1), n為鏈表長度
    class Solution19 {
        class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            //用快慢指標
            ListNode fast = head;
            ListNode dummy = new ListNode(-1, head);
            ListNode slow = dummy;

            //讓快指針先走n
            for(int i=0; i<n; i++)
                fast = fast.next;

            while(fast != null){
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;

            return dummy.next;

        /*
              1 -> 2 -> 3 -> 4 -> 5 -> null, n = 2
                             ^slow(原) ^fast

        如果直接用head給slow走, 會走超過一格
        且slow = slow.next也只是改變指標位子

        -1 -> 1 -> 2 -> 3 -> 4 -> 5
                        ^slow(dummy)

        用dummy不僅可以剛好, 也可以使用slow.next = slow.next.next, 才能真正改變鏈表指向
        */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1), n為鏈表長度
    class Solution143 {
        class ListNode {
            int val;
            ListNode next;
            ListNode() {}
            ListNode(int val) { this.val = val; }
            ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        }

        public void reorderList(ListNode head) {
            //鏈表中點 + 逆序 + 合併

            //1. 找到中點並分段
            ListNode mid = findMid(head);//1 -> 2 -> (3) -> 4 -> 5
            ListNode a = head;//a = 1 -> 2 -> (3) -> 4 -> 5 -> null
            ListNode b = mid.next;//b = 4 -> 5 -> null
            mid.next = null;//斷開鏈表, a = 1 -> 2 -> (3) -> null

            //2. 將後半段逆序

            b = reverse(b);

            //3. 將兩者合併
            merge(a, b);
        }

        public ListNode findMid(ListNode head){
            ListNode fast = head;
            ListNode slow = head;

            while(fast.next != null && fast.next.next != null){
                fast = fast.next.next;
                slow = slow.next;
            }

            return slow;
            /*
            偶數情況:
                1 -> 2 -> 3 -> 4 -> null
                     ^s   ^f(f.next.next == null)
            奇數情況:
                1 -> 2 -> 3 -> 4 > 5 -> null
                          ^s       ^f(f.next == null)

            */
        }

        public ListNode reverse(ListNode head){
            ListNode pre = null;
            ListNode cur = head;
            while(cur != null){
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;//滾動指標
                cur = next;//滾動指標
            }
            return pre;
        }

        public void merge(ListNode a, ListNode b){
            ListNode dummy = new ListNode();
            ListNode tail = dummy;

            //依照findMid, a始終會比b長或等於
            while(a != null && b != null){
                ListNode nextA = a.next;
                ListNode nextB = b.next;
                a.next = b;
                a = nextA;//移動a指標
                b.next = a;
                b = nextB;//移動b指標
            }

            /* 傳入的是指標, 不會影響外層head的ListNode

            v
            a1 -> a2 -> a3 -> null

            b1 -> b2 -> null
            ^
            -----------------------
            a.next = b, 改變鏈節指向

            v
            a1    a2 -> a3 -> null
            V
            b1 -> b2 -> null
            ^
            -----------------------
            a = nextA, 移動指標

                  v
            a1    a2 -> a3 -> null
            V
            b1 -> b2 -> null
            ^
            -----------------------
            b.next = a, 改變鏈節指向

                  v
            a1  > a2 -> a3 -> null
            V  /
            b1    b2 -> null
            ^
            -----------------------
            b = nextB, 移動指標

                  v
            a1  > a2 -> a3 -> null
            V  /
            b1    b2 -> null
                  ^
            -----------------------
            ...

                        v
            a1  > a2  > a3 -> null
            V  /  V  /
            b1    b2    null
                        ^
            */
        }
    }

}