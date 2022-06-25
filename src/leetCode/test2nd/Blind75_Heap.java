package leetCode.test2nd;

import java.util.*;

public class Blind75_Heap {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    //*Iteration + Merge
    class Solution23 {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists.length == 0) return null;
            if(lists.length == 1) return lists[0];
            return split(lists, 0, lists.length - 1);
        }

        public ListNode split(ListNode[] lists, int start, int end){
            if(start == end) return lists[start];

            int mid = (start + end) / 2;
            ListNode left = split(lists, start, mid);
            ListNode right = split(lists, mid + 1, end);

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

    //*Heap
    class Solution347 {
        public int[] topKFrequent(int[] nums, int k) {
            //must be better than O(n log n)
            Map<Integer, Integer> counts = new HashMap<>();
            for(int num : nums)
                counts.put(num, counts.getOrDefault(num, 0) + 1);

            PriorityQueue<Map.Entry<Integer, Integer>> queue
                    = new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());

            for(Map.Entry<Integer, Integer> entry : counts.entrySet()){
                queue.offer(entry);//O(logn)
                if(queue.size() > k)
                    queue.poll();//拉掉比較小的
            }

            int size = queue.size();//記得要把size抓出, 避免迴圈動態錯誤
            int[] res = new int[size];
            for(int i=0; i<size; i++)
                res[i] = queue.poll().getKey();

            return res;
        }
    }

    //*Heap
    class Solution295 {
        class MedianFinder {
            public PriorityQueue<Integer> smallQue;
            public PriorityQueue<Integer> bigQue;

            public MedianFinder() {
                smallQue = new PriorityQueue<>((i1, i2) -> i2 - i1);//大到小
                bigQue = new PriorityQueue<>((i1, i2) -> i1 - i2);//小到大
            }

            public void addNum(int num) {
                if (smallQue.isEmpty() || num < smallQue.peek()) {
                    smallQue.offer(num);
                    if (smallQue.size() > bigQue.size() + 1)
                        bigQue.offer(smallQue.poll());
                } else {
                    bigQue.offer(num);
                    if (smallQue.size() < bigQue.size())//記得大到小也要平衡
                        smallQue.offer(bigQue.poll());
                }
            }

            public double findMedian() {
                if (smallQue.size() == bigQue.size()) {//even
                    return (smallQue.peek() + bigQue.peek()) / 2.0;
                } else {
                    return smallQue.peek();
                }
            }
        }
    }

}