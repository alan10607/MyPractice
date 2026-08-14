package leetCode.java;

import java.util.*;

//Intervals Heap O(nlogn + qlogq) O(n + q), n = intervals.length, q = queries.length
class Solution1851 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // Offline Query, 先記錄queries index, 之後計算完再重新放回原位置
        int n = queries.length;
        int[][] qs = new int[n][2]; // [數字][原queries index] 
        for (int i = 0; i < n; ++i) {
            qs[i][0] = queries[i];
            qs[i][1] = i;
        }

        // 排序依照start與qs, 方便配對
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 依照start小到大
        Arrays.sort(qs, (a, b) -> a[0] - b[0]); // 依照queries數字小到大
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // <[長度][right index]>, 依照長度小到大

        int i = 0;
        int[] res = new int[n];
        for (int[] q : qs) {
            int query = q[0];
            int originalIndex = q[1];

            // 尋找start<=q的值, 放入篩選
            while (i < intervals.length && intervals[i][0] <= query) {
                pq.offer(new int[]{intervals[i][1] - intervals[i][0] + 1, intervals[i][1]}); // [長度][right index]
                ++i;
            }

            // 把right<q的值移出
            while (!pq.isEmpty() && pq.peek()[1] < query) {
                pq.poll();
            }

            res[originalIndex] = pq.isEmpty() ? -1 : pq.peek()[0]; // peek()[0]=最短值
        }
        return res;
    }
}
/* intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]


        heap(len小到大):

        s   e   len
q[0]=2  2   3   2
        2   5   4
        1   8   8

---------------------
q[0]=5  2   5   4
        1   8   8

---------------------
q[0]=19

---------------------
q[0]=22 20  25  5


*/