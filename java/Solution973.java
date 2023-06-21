package leetCode.java;

import java.util.*;

//Heap O(nlogk) O(k)
class Solution973 {
    public int[][] kClosest(int[][] points, int k) {
        //<x,y,距離>, 距離遠到近排列
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        for(int[] point : points){
            pq.offer(new int[]{point[0], point[1], point[0] * point[0] + point[1] * point[1]});
            if(pq.size() > k)
                pq.poll();
        }

        int[][] res = new int[pq.size()][2];
        for(int i=0; i<res.length; i++){
            int[] point = pq.poll();
            res[i][0] = point[0];
            res[i][1] = point[1];
        }
        return res;
    }
}