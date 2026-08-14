package leetCode.java;

import java.util.*;

//Intervals O(n) O(n)
class Solution57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Intervals is sorted in ascending order by start -> 題目已經排序過, 可以照位置順序處理
        List<int[]> inserted = new ArrayList<>();
        int i = 0, n = intervals.length;
        // 1. new在interval右邊, [interval]<new>, 直接放入
        while (i < n && intervals[i][1] < newInterval[0]) {
            inserted.add(intervals[i]);
            ++i;
        }

        // 2. 重疊, 有可能是[<]>,[<>],<[>],<[]>, 合併
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            ++i;
        }
        inserted.add(newInterval);

        // 3. new在intervals[i]左邊, <new>[interval], 放入剩下
        while (i < n) {
            inserted.add(intervals[i]);
            ++i;
        }

        
        // 轉回int[][]
        int[][] res = new int[inserted.size()][2];
        for (int j = 0; j < res.length; ++j) {
            res[j] = inserted.get(j);
        }

        return res;
    }
}
/*
1. new 在 interval 右邊, intervals[i][1] < newInterval[0]
interval    ----
new                 ----

2. 重疊的情況, intervals[i][1] >= newInterval[0] && intervals[i][0] <= newInterval[1]
interval    --------
new               ------

interval    -----------
new               ---

interval       -------
new         ------

interval       ----
new         ------------

3. 剩下的, new 在 interval 左邊, intervals[i][0] > newInterval[1]
interval            ----
new         ------

*/