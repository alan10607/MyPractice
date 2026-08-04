package leetCode.java;

import java.util.*;

//Stack O(n) O(n)
class Solution739 {
    public int[] dailyTemperatures(int[] temperatures) {
        // 這題本質是找: 每個元素右邊第一個比它大的元素 -> Monotonic
        Deque<Integer> st = new ArrayDeque<>(); // 存index, 對應溫度遞減
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; ++i) {
            while (!st.isEmpty() && temperatures[st.peek()] < temperatures[i]) {
                int prev = st.pop(); // 前一個比較小的溫度位置
                res[prev] = i - prev; // 計算天數間隔
            }
            st.push(i);
        }
        return res;
    }
}
/* 
index        =   0  1  2  3  4  5  6  7
temperatures = [73,74,75,71,69,72,76,73]
i=0, stack=[0]
i=1, res[0]=1-0, stack=[1]
i=2, res[1]=2-1, stack=[2]
i=3, stack=[3,2]
i=4, stack=[4,3,2]
i=5, res[4]=5-4, res[3]=5-3, stack=[5,2]
i=6, res[5]=6-5, res[2]=6-2, stack=[6]
i=7, stack=[7,6]

res=[1,1,4,2,1,1,0,0]
*/