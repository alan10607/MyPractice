package leetCode.java;

import java.util.*;

//Intervals O(n) O(n)
class Solution57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merge = new ArrayList<>();
        boolean alreadyInsert = false;
        int start = newInterval[0];
        int end = newInterval[1];
        for(int[] interval : intervals){
            if(interval[1] < start){
                //在最左側
                merge.add(interval);
            }else if(end < interval[0]){
                //在最右側
                if(!alreadyInsert){
                    merge.add(new int[]{start, end});
                    alreadyInsert = true;
                }
                merge.add(interval);
            }else{
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            }
        }

        if(!alreadyInsert)//若還沒加入, 記得在最後加入
            merge.add(new int[]{start, end});

        int[][] res = new int[merge.size()][2];
        for(int i=0; i<merge.size(); i++)
            res[i] = merge.get(i);

        return res;
    }
}
/*
---  ---  ---  ---
      -----

*/