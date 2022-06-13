package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_Interval {

    //Time Complexity: O(n), Space Complexity: O(1), n = intervals.length
    class Solution57 {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            //題目給的intervals已經排列好, 而且須要回傳已排列的
            List<int[]> merge = new ArrayList<int[]>();
            int left = newInterval[0];
            int right = newInterval[1];
            boolean alreadyInsert = false;

            for(int[] interval : intervals){
                if(interval[1] < left){
                    merge.add(interval);//代表在左方, 無交集
                }else if(right < interval[0]){
                    //因為已排序, 最後才會進到這個if
                    if(!alreadyInsert){
                        merge.add(new int[]{left, right});//插入新區間
                        alreadyInsert = true;
                    }

                    merge.add(interval);//代表在右方, 無交集
                }else{
                    //合併範圍
                    left = Math.min(left, interval[0]);
                    right = Math.max(right, interval[1]);
                }
            }

            //若還沒插入, 則在最右方插入
            if(!alreadyInsert)
                merge.add(new int[]{left, right});

            //List轉成int[]
            int[][] res = new int[merge.size()][2];
            for(int i=0; i<res.length; i++)
                res[i] = merge.get(i);

            return res;
        }
    }

    //Time Complexity: O(n logn), Space Complexity: O(logn), 主要是排序所需複雜度
    class Solution56 {
        public int[][] merge(int[][] intervals) {
            //先依照左側排序, O(n logn)
            Arrays.sort(intervals, (interval1, interval2) -> interval1[0] - interval2[0]);
            /*
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[0] - interval2[0];
                }
            });
            */
            List<int[]> merge = new ArrayList<int[]>();

            for(int i=0; i<intervals.length; i++){
                int size = merge.size();
                if(size == 0 || merge.get(size - 1)[1] < intervals[i][0]){
                    //如果是第一個, 或是比新的區間左側比目前右側大(即不重疊)
                    merge.add(intervals[i]);
                }else{
                    //更新成更大的右側
                    merge.get(size - 1)[1] = Math.max(merge.get(size - 1)[1], intervals[i][1]);
                }
            }

            //List轉成int[]
            int[][] res = new int[merge.size()][2];
            for(int i=0; i<res.length; i++)
                res[i] = merge.get(i);

            return res;
        }
    }

    //Time Complexity: O(n logn), Space Complexity: O(logn), 主要是排序所需複雜度
    class Solution435 {
        public int eraseOverlapIntervals(int[][] intervals) {
            //透過排列模擬可以有最快算法
            //依右側進行排序
            Arrays.sort(intervals, (i1, i2) -> i1[1]- i2[1]);
            int count = 0;

            int right = intervals[0][1];
            //已設定0, 從1開始
            for(int i=1; i<intervals.length; i++){
                //建立新的區間, 等於也要算
                if(right <= intervals[i][0]){
                    right = intervals[i][1];//[1, 3], [3, 5]
                }else{
                    count++;//[1, 3], [2, 4], 此時重疊, 不用更新right因為新的right會更大
                }
            }

            return count;

            /* 比較會有4種情況
                1-------3

                        3-------5 //此時建立新的區間, 更新right
                    2-------4 //表示重疊, 因為已排序所以新的right一定比舊的大, 要找最靠左的right
            0-------2 //不可能發生, 因為已排序
            0---1 //不可能發生, 因為已排序

            */
        }
    }

    //Time Complexity: O(n logn), Space Complexity: O(logn), 主要是排序所需複雜度
    class Solution252 {
        class Interval {
            int start, end;
            Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        public boolean canAttendMeetings(List<Interval> intervals) {
            //其實就是比對區間有無交集, 直接用排序
            Collections.sort(intervals, (i1, i2) -> i1.start - i2.start);
            for(int i=1; i<intervals.size(); i++){
                if(intervals.get(i).start < intervals.get(i - 1).end)
                    return false;
            }
            return true;
        }
    }

    //Time Complexity: O(n logn), Space Complexity: O(logn), 主要是排序所需複雜度
    public class Solution253 {
        class Interval {
            int start, end;
            Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }

        public int minMeetingRooms(List<Interval> intervals) {
            //透過維護兩組數組
            int[] start = new int[intervals.size()];
            int[] end = new int[intervals.size()];
            for(int i=0; i<intervals.size(); i++){
                start[i] = intervals.get(i).start;
                end[i] = intervals.get(i).end;
            }

            //分別進行排序
            Arrays.sort(start);
            Arrays.sort(end);

            int s = 0;
            int e = 0;
            int room = 0;
            int max = 0;
            while(s < start.length){
                /*  s1----------e1
                        s2---------
                            s3-----
                */
                if(start[s] < end[e]){
                    //計算e之前有幾個s, 每個s都要開一個獨立的會議室
                    room++;
                    s++;
                }else{
                    //新的s在等於或大於e之後, 代表是在這個會議結束之後, 減少會議室並往後查看e
                    room--;
                    e++;
                }
                max = Math.max(max, room);
            }

            return max;
        }

        /*
        intervals = [(0,30),(5,10),(15,20)]
        start = [0, 5, 15]
        end = [10, 20, 30]

                    0-----------------------30
                        5---10
                                15--20
        room =  0   1   2   1   2
        s =     0   1   2   2   2
        e =     0   0   0   1   2
        */
    }

}