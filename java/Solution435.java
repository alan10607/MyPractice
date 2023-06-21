package leetCode.java;

import java.util.*;

//Intervals O(nlogn) O(n)
class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        //return the minimum number of intervals that need to remove
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);//依照右界排列

        int res = 0;
        int right = Integer.MIN_VALUE;
        for(int[] interval : intervals){
            if(interval[0] < right){
                res++;//左界在上一個的右界內, 有交集
            }else{
                right = interval[1];//更新最右側
            }
        }
        return res;
    }
}
/*
-----   -- ----
      ------
   ------
*/