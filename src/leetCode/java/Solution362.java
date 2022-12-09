package leetCode.java;

import java.util.*;

//HitCounter(), hit(): O(1) O(n), getHits(): O(m), O(n), n為儲存的timestamp數量, m為5分鐘以前的timestamp數量
class HitCounter {//Solution362
    Queue<Integer> time;

    public HitCounter() {
        time = new LinkedList<>();
    }

    public void hit(int timestamp) {
        time.offer(timestamp);
    }

    public int getHits(int timestamp) {
        while(!time.isEmpty() && time.peek() + 300 <= timestamp)
            time.poll();

        return time.size();
    }
}