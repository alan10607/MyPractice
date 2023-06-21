package leetCode.java;

import java.util.*;

//Interval O(m + n) O(1), m = firstList.length, n = secondList.length;
class Solution986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0, j = 0;
        List<int[]> res = new ArrayList<>();
        while(i < firstList.length && j < secondList.length){
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end   = Math.min(firstList[i][1], secondList[j][1]);
            if(start <= end) res.add(new int[]{start, end});

            if(firstList[i][1] < secondList[j][1]){//以end作為下一個標準
                ++i;
            }else{
                ++j;
            }
        }

        return res.toArray(new int[res.size()][]);
    }
}