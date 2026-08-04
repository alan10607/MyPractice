package leetCode.java;

import java.util.*;

//Stack O(nlogn) O(n) 時間複雜度為排序所需
class Solution853 {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] cars = new int[n][2]; // [[位置, 速度], ...]
        for (int i = 0; i < n; ++i) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> a[0] - b[0]); // 依照位置小排到大

        Deque<Double> st = new ArrayDeque<>();
        for (int[] car : cars) {
            // time = (target - position) / speed
            double time = ((double) target - car[0]) / car[1]; 
            while (!st.isEmpty() && st.peek() <= time) { // 相同也會變成一fleet
                st.pop();
            }
            st.push(time);
        }
        return st.size();
    }
}
/*
ex: position = [10,8,0,5,3], speed = [2,4,1,1,3]

position:   0   3   5   8   10
speed:      1   3   1   4    2
time:       12  3   7   1    1


塞車代表比較慢的會卡住快的, 也就是時間花費多的卡住時間少的
monotonic: 保持time遞增, stack保持底部最大
---------------->
12    3,7    1,1

stack:
[12.0]
[3.0, 12.0]
[7.0, 12.0]
[1.0, 7.0, 12.0]
[1.0, 7.0, 12.0]

*/