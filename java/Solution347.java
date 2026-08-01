package leetCode.java;

import java.util.*;

//Heap O(nlogk) O(n), n = nums.length
class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>(); // <num, 出現次數>
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // 依照出現次數少到多
        PriorityQueue<Integer> pq = new PriorityQueue((a, b) -> counts.get(a) - counts.get(b));
        for (int num : counts.keySet()) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll(); // 移除最少的
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}