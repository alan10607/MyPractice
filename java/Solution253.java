package leetCode.java;

import java.util.*;

//Intervals O(nlog) O(n), sort需要O(nlogn) O(n)
class Solution253 {//lintcode919
    public int minMeetingRooms(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; ++i) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start); // 分別把start, end由小到大排序
        Arrays.sort(end);

        int s = 0, e = 0; // start / end index
        int res = 0, count = 0;
        while (s < start.length) {
            if (start[s] < end[e]) { // 會議衝突, 新增一個新的room
                ++count;
                res = Math.max(res, count);
                ++s;
            } else { // start[s] <= end[e], 可以結束一個會議
                --count;
                ++e;
            }
        }
        return res;
    }
}
/* intervals = [(0,30),(5,10),(15,20)]
0   5   10  15  20      30
--------------------------
    ------  ------

*/


//Intervals O(nlog) O(n), sort需要O(nlogn) O(n)
class Solution253_2 {//lintcode919
    public int minMeetingRooms(List<Interval> intervals) {
        // 也可以用pq做, 但時間上pq每次插入移除都需要維護heap會比較慢
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> a.end - b.end);
        for (Interval interval : intervals) {
            // 找最快結束的會議有沒有空位
            if (!pq.isEmpty() && pq.peek().end <= interval.start) {
                pq.poll(); // 有空位, 用這次的會議end取代下次判斷
            }
            pq.offer(interval);
        }
        return pq.size();
    }
}

