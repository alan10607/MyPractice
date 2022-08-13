package leetCode.java;

import java.util.*;

//Heap O(tlogt) O(Z), t = tasks.length, Z為tasks的種類
class Solution621 {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> counts = new HashMap<>();
        for(char ch : tasks)
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);//多排到少
        for(Map.Entry<Character, Integer> entry : counts.entrySet())
            pq.offer(entry.getValue());

        int period = n + 1;//A要冷卻2次的話, 週期為3
        int res = 0;
        while(!pq.isEmpty()){
            List<Integer> remain = new ArrayList<>();
            int time = 0;
            while(!pq.isEmpty() && time < period){
                time++;
                int count = pq.poll() - 1;
                if(count > 0)
                    remain.add(count);
            }

            //remain為空則表示是最後一次
            res += remain.isEmpty() ? time : period;

            for(int count : remain)
                pq.offer(count);//沒做完的task放回
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