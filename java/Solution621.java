package leetCode.java;

import java.util.*;

//Heap O(tlogt) O(Z), t = tasks.length, Z為tasks的種類
class Solution621 {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char task : tasks) {
            counts.put(task, counts.getOrDefault(task, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // max heap
        for (int count : counts.values()) {
            pq.offer(count);
        }

        int res = 0;
        int period = n + 1; // 任務之間間隔n的話, 實際上週期是n+1, ex: A要冷卻2次的話, 週期為3[A,B,C,A...]
        while (!pq.isEmpty()) {
            List<Integer> standBy = new ArrayList<>();
            int time = 0;
            while (time < period && !pq.isEmpty()) {
                int remain = pq.poll() - 1;
                if (remain > 0) {
                    standBy.add(remain);
                }
                ++time;
            }

            // standBy為空則表示是最後一次
            res += standBy.isEmpty() ? time : period;

            for (int remain : standBy) {
                pq.offer(remain); // 沒做完的task放回
            }
        }
        return res;
    }
}
/* tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2

週期為3
ABC
ADE
AFG
Axx
Axx
A
=>16
*/