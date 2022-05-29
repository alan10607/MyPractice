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

}