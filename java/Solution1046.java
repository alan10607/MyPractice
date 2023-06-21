package leetCode.java;

import java.util.*;

//Heap O(nlogn) O(n), n = stones.length
class Solution1046 {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);//大到小
        for(int stone : stones)
            pq.offer(stone);

        while(pq.size() > 1){
            int sub = pq.poll() - pq.poll();
            if(sub > 0)
                pq.offer(sub);
        }
        return pq.isEmpty() ? 0 : pq.peek();//要判斷empty的情況
    }
}