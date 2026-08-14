package leetCode.java;

import java.util.*;

//Intervals O(nlogn) O(n)
class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 盡量多保留interval, 回傳最少要移除的量
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // 依照end小排到大, 可以最緊湊
        int end = Integer.MIN_VALUE, res = 0;
        for (int[] interval : intervals) {
            if (interval[0] < end) { // 左界在上一個的右界內, 有交集, 移除
                ++res;
                continue;
            }
            end = interval[1]; // 更新最右側
        }

        return res;
    }
}