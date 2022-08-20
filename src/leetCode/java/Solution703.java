package leetCode.java;

import java.util.*;

//Heap KthLargest(): O(nlogk) O(k) add(): O(logk) O(k), n = nums.length
class KthLargest {//Solution703
    public PriorityQueue<Integer> pq;//小排到大
    public int size;

    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        size = k;
        for(int num : nums)
            add(num);
    }

    public int add(int val) {
        //returns the kth largest element
        pq.offer(val);
        if(pq.size() > size)
            pq.poll();

        return pq.peek();
    }
}