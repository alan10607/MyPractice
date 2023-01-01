package leetCode.java;

import java.util.*;

//Interval DP Binary Search O(nlogn) O(n), n = job.size(), upper_bound()需要O(logn)
class Solution1235 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<int[]> jobs = new ArrayList<>();//<start, end, profit>
        for(int i=0; i<startTime.length; i++)
            jobs.add(new int[]{startTime[i], endTime[i], profit[i]});

        Collections.sort(jobs, (a, b) -> a[1] - b[1]);//依照end小排到大

        TreeMap<Integer, Integer> dp = new TreeMap<>();//<end, 累積profit>
        dp.put(0, 0);
        for(int[] job : jobs){
            int last = dp.get(dp.floorKey(job[0]));//floorKey, 找出小於或等於該數之最大值
            int sumProfit = last + job[2];
            if(dp.lastEntry().getValue() < sumProfit)//利潤較大才可加入
                dp.put(job[1], sumProfit);
        }
        return dp.lastEntry().getValue();
    }
}