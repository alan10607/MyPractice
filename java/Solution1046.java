package leetCode.java;

import java.util.*;

//Heap O(nlogn) O(n), n = stones.length
class Solution1046 {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // max heap
        for (int stone : stones) {
            pq.offer(stone);
        }
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a - b > 0) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.peek(); // 要判斷empty的情況
    }
}