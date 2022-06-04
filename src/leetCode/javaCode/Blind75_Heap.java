package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_Heap {

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
            //直接用合併排序的概念
            if(lists.length == 0) return null;
            if(lists.length == 1) return lists[0];

            return split(lists, 0, lists.length - 1);
        }

        public ListNode split(ListNode[] lists, int start, int end){
            //設定返回條件, 如果已經不能細分就回傳這個ListNode
            if(start == end) return lists[start];

            int mid = (start + end) / 2;
            ListNode leftPart = split(lists, start, mid);
            ListNode rightPart = split(lists, mid + 1, end);

            return merge(leftPart, rightPart);

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

            //加入剩餘
            tail.next = a == null ? b : a;

            return dummy.next;
        }
    }

    //Time Complexity: O(n logk), Space Complexity: O(n), n = nums.length, k為要求最常出現的前k個數字
    //時間每次操作queue需要logk, 共n logk, 空間Map共需要n
    class Solution347 {
        public int[] topKFrequent(int[] nums, int k) {
            //要找出最常出現的前k個數字, 如果全掃一次數量, 還要再經過排序才找得到, 乾脆直接維護一個PirorityQuene
            //!!PriorityQueue預設是第一個為最小(poll時會拉最小)，相當於二元小堆積
            //int[0]為數字, int[1]為出現次數
            PriorityQueue<int[]> queue = new PriorityQueue<int[]>((i1, i2) -> i1[1] - i2[1]);
            /*
            Map.Entry + Comparator寫法:
            PriorityQueue<Map.Entry<Integer, Integer>> queue
                = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                        return e1.getValue() - e2.getValue();
                    }
                });
            */
            Map<Integer, Integer> count = new HashMap<Integer, Integer>();

            //先跑一次計算數量
            for(int num : nums)
                count.put(num, count.getOrDefault(num, 0) + 1);

            //放入PriorityQueue
            for(Map.Entry<Integer, Integer> entry : count.entrySet()){
                //Map.Entry轉成int[]比較方便
                int[] temp = new int[]{entry.getKey(), entry.getValue()};//Integer運算會自動解包裝
                queue.offer(temp);//需要O(logn)

                if(queue.size() > k)
                    queue.poll();//O需要(logn), 去掉最小的
            }

            int[] res = new int[queue.size()];
            int size = queue.size();
            for(int i=0; i<size; i++)
                res[i] = queue.poll()[0];

            return res;
        }
    }

}