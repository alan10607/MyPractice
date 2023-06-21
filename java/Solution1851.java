package leetCode.java;

import java.util.*;

//Intervals Heap O(nlogn + qlogq) O(n + q), n = intervals.length, q = queries.length
class Solution1851 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int[][] que = new int[queries.length][2];//[[數字, 排序前的位置], ...]
        for(int i=0; i<queries.length; i++){
            que[i][0] = queries[i];
            que[i][1] = i;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);//依照左側排列, 配合query入列
        Arrays.sort(que, (a, b) -> a[0] - b[0]);//依照位子順序排列

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);//<[end, 長度], ...>, 由長度大到小
        int[] res = new int[queries.length];
        int i = 0;
        for(int[] query : que){
            int num = query[0];
            int index = query[1];

            //範圍內的interval加入pq
            while(i < intervals.length && intervals[i][0] <= num){//相等也算
                int len = intervals[i][1] - intervals[i][0] + 1;
                pq.offer(new int[]{intervals[i][1], len});
                i++;
            }

            //範圍外的移出
            while(!pq.isEmpty() && pq.peek()[0] < num)
                pq.poll();

            res[index] = pq.isEmpty() ? -1 : pq.peek()[1];
        }
        return res;
    }
}
/* intervals = [[1,8],[2,3],[2,5],[20,25]], queries = [2,5,19,22]

queries=2, heap=[[2,3],[4,5],[8,8]], res=2
queries=5, heap=[[4,5],[8,8]], res=4
queries=19, heap=[], res=-1
queries=22, heap=[[6,25]], res=6
*/