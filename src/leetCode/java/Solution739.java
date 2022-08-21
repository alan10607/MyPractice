package leetCode.java;

import java.util.*;

//Stack O(n) O(n)
class Solution739 {
    public int[] dailyTemperatures(int[] temperatures) {
        //這題用暴力法會超過時間
        int[] res = new int[temperatures.length];
        Deque<int[]> deque = new LinkedList<>();//<[溫度, 日期], ...>
        for(int i=0; i<temperatures.length; i++){
            while(!deque.isEmpty() && deque.peek()[0] < temperatures[i]){
                int[] data = deque.poll();
                res[data[1]] = i - data[1];//計算日期
            }
            deque.push(new int[]{temperatures[i], i});
        }
        return res;
    }
}
/* temperatures = [71,69,70,76,73] push入stack, 若有比較大的就pop
stack=71
stack=69,71
stack=70,71
stack=76
*/