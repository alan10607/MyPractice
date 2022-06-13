package leetCode.test2nd;

import java.util.*;

public class Blind75_Interval {
    class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //Simulation
    class Solution57 {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> merge = new ArrayList<>();
            int l = newInterval[0];
            int r = newInterval[1];
            boolean insertFlag = false;
            for(int[] interval : intervals){
                if(interval[1] < l){
                    merge.add(interval);
                }else if(r < interval[0]){
                    if(!insertFlag){
                        merge.add(new int[]{l, r});
                        insertFlag = true;
                    }
                    merge.add(interval);
                }else{
                    l = Math.min(l, interval[0]);
                    r = Math.max(r, interval[1]);
                }
            }

            if(!insertFlag)
                merge.add(new int[]{l, r});

            int[][] res = new int[merge.size()][2];
            for(int i=0; i<merge.size(); i++)
                res[i] = merge.get(i);

            return res;
        }
    }

    //*Simulation
    class Solution56 {
        public int[][] merge(int[][] intervals) {
            //以左側為標準排序, 以獲得最大左區間
            Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

            int r = -1;
            List<int[]> merge = new ArrayList<>();
            for(int i=0; i<intervals.length; i++){
                if(merge.size() == 0 || merge.get(merge.size() - 1)[1] < intervals[i][0]){
                    merge.add(intervals[i]);//新增區間
                    r = intervals[i][1];
                }else if(r < intervals[i][1]){
                    r = intervals[i][1];
                    merge.get(merge.size() - 1)[1] = r;//更新原本的右側
                }
            }

            int[][] res = new int[merge.size()][2];
            for(int i=0; i<merge.size(); i++)
                res[i] = merge.get(i);

            return res;
        }
    }

    //*Simulation
    class Solution252 {
        public boolean canAttendMeetings(List<Interval> intervals) {
            Collections.sort(intervals, (i1, i2) -> i1.end - i2.end);
            for(int i=1; i<intervals.size(); i++){
                if(intervals.get(i).start < intervals.get(i - 1).end)
                    return false;
            }
            return true;
        }
    }

    //*Simulation
    public class Solution253 {
        public int minMeetingRooms(List<Interval> intervals) {
            int[] start = new int[intervals.size()];
            int[] end = new int[intervals.size()];
            for(int i=0; i<intervals.size(); i++){
                start[i] = intervals.get(i).start;
                end[i] = intervals.get(i).end;
            }

            Arrays.sort(start);
            Arrays.sort(end);

            int room = 0;
            int max = 0;
            int s = 0;
            int e = 0;
            while(s < start.length){
                if(start[s] < end[e]){
                    room++;
                    s++;
                }else{
                    room--;
                    e++;
                }
                max = Math.max(max, room);
            }
            return max;
        }
    }

}