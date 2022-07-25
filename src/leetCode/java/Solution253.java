package leetCode.java;

import java.util.*;

//Intervals O(nlog) O(n), sort需要O(nlogn) O(n)
class Solution253 {//lintcode919
    public int minMeetingRooms(List<Interval> intervals) {
        //將start與end分別拿出排序
        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        for(int i=0; i<intervals.size(); i++){
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);

        //模擬
        int s = 0;
        int e = 0;
        int room = 0;
        int res = 0;
        while(s < start.length){
            if(start[s] < end[e]){
                s++;
                room++;
                res = Math.max(res, room);
            }else{
                e++;
                room--;
            }
        }
        return res;
    }
}
/*
0-------------------30
	5---10
		10------20

*/