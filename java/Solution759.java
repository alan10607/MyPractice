package leetCode.java;

import java.util.*;

//O(nlogn) O(n), n為schedule內所有時段長度
class Solution759 {//lintcode850
    public List<Interval> employeeFreeTime(int[][] schedule) {
        List<int[]> intervals = new ArrayList<>();
        for(int[] sch : schedule){
            for(int i=0; i<sch.length; i+=2){
                intervals.add(new int[]{sch[i], sch[i + 1]});
            }
        }

        Collections.sort(intervals, (a, b) -> a[0] - b[0]);
        List<Interval> res = new ArrayList<>();
        int last = -1;
        for(int[] interval : intervals){
            if(last != -1 && last < interval[0]){
                res.add(new Interval(last, interval[0]));
            }
            last = Math.max(last, interval[1]);
        }
        return res;
    }
}