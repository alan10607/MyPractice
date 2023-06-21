package leetCode.java;

import java.util.*;

//Intervals O(nlogn) O(n), sort需要O(nlogn) O(n)
class Solution56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);//依照左界排序
        List<int[]> merge = new ArrayList<>();
        for(int[] interval : intervals){
            //用last指向上一個新增的interval
            int[] last = merge.isEmpty() ? new int[]{0, 0} : merge.get(merge.size() - 1);
            if(merge.isEmpty() || last[1] < interval[0]){
                //新增新的區段
                merge.add(interval);
            }else{
                //更新已有的區段的右側
                last[1] = Math.max(last[1], interval[1]);
            }
        }

        int[][] res = new int[merge.size()][2];
        for(int i=0; i<merge.size(); i++)
            res[i] = merge.get(i);

        return res;
    }
}
/*
可能有的情況
------
 ----
	-----
*/