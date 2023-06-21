package leetCode.java;

import java.util.*;

//Intervals O(nlogn) O(n), sort需要O(nlogn) O(n)
class Solution252 {//lintcode920
    public boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        for(int i=1; i<intervals.size(); i++){
            if(intervals.get(i - 1).end > intervals.get(i).start)
                return false;
        }
        return true;
    }
}