package leetCode.java;

import java.util.*;

//Intervals O(nlogn) O(n), sort需要O(nlogn) O(n)
class Solution56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 依照左界排序

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int[] last = merged.get(merged.size() - 1); // 用last指向上一個新增的interval
            if (last[1] < intervals[i][0]) {
                merged.add(intervals[i]);
            } else {
                last[1] = Math.max(last[1],intervals[i][1]);
            }
        }

        int[][] res = new int[merged.size()][2];
        for (int j = 0; j < res.length; ++j) {
            res[j] = merged.get(j);
        }
        return res;
    }
}