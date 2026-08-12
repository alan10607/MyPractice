package leetCode.java;

import java.util.*;

//Heap KthLargest(): O(nlogk) O(k) add(): O(logk) O(k), n = nums.length
class KthLargest {//Solution703
    PriorityQueue<Integer> pq; // min heap, 小到大
    int maxSize;

    public KthLargest(int k, int[] nums) {
        maxSize = k;
        pq = new PriorityQueue<>();
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        //returns the kth largest element
        pq.offer(val);
        if (pq.size() > maxSize) {
            pq.poll();
        }
        return pq.peek();
    }
}