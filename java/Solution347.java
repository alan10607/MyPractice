package leetCode.java;

import java.util.*;

//Heap O(nlogk) O(n), n = nums.length
class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();//<數字, 出現次數>
        for(int num : nums)
            counts.put(num, counts.getOrDefault(num, 0) + 1);

        //PriorityQueue沒有pollLast(), 由出現次數小排到大
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);//<[數字, 出現次數], ...>
        for(Map.Entry<Integer, Integer> entry : counts.entrySet()){
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
            if(pq.size() > k)
                pq.poll();
        }

        int[] res = new int[k];
        for(int i=0; i<k; i++)
            res[i] = pq.poll()[0];

        return res;
    }
}