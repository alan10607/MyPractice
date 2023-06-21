package leetCode.java;

import java.util.*;

//Stack O(nlogn) O(n) 時間複雜度為排序所需
class Solution853 {
    public int carFleet(int target, int[] position, int[] speed) {
        int[][] cars = new int[position.length][2];//[[位置, 速度], ...]
        for(int i=0; i<position.length; i++){
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> a[0] - b[0]);//依位置排列
        Deque<Double> deque = new LinkedList<>();//保持底部最大
        for(int[] car : cars){
            double time = (double) (target - car[0]) / car[1];
            while(!deque.isEmpty() && deque.peek() <= time)
                deque.poll();//前一個車被卡住

            deque.push(time);
        }
        return deque.size();
    }
}
/* 透過所需時間判斷塞車
target = 12
position = [10, 8, 0, 5, 3]
speed =    [ 2, 4, 1, 1, 3]
time =       1  1 12  7  3

stack:
1
1->x
7
3->x
12
*/