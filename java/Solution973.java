package leetCode.java;

import java.util.*;

//Heap O(nlogk) O(k)
class Solution973 {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]) 
        ); // max heap, 依照距離大到小
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > k) { // 拿掉大的
                pq.poll();
            }
        }

        int[][] res = new int[pq.size()][2];
        for (int i = 0; i < res.length; ++i) {
            res[i] = pq.poll();
        }
        return res;
    }
}