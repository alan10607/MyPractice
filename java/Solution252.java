package leetCode.java;

import java.util.*;

//Intervals O(nlogn) O(n), sort需要O(nlogn) O(n)
class Solution252 {
    public boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> a.start - b.start);

        for (int i = 1; i < intervals.size(); ++i) {
            // 重疊條件: a.end < b.start && a.start < b.end
            // a.start < b.end在排序時已經確定
            // 不排序的話就要所有intervals互相比較, 時間O(n^2)更慢
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false;
            }
        }
        return true;
    }
}