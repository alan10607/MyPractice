package leetCode.java;

import java.util.*;

//Stack O(n) O(n)
class Solution84 {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> st = new ArrayDeque<>(); // 放入index, 保持底部height[i]最小
        int res = 0, n = heights.length;
        for (int i = 0; i <= n; ++i) {
            int curHeight = (i == n) ? 0 : heights[i]; // i==heigths.length時計算最後一塊
            while (!st.isEmpty() && heights[st.peek()] > curHeight) {
                // 遇到了比較矮的方形, 這時候計算前面比較高的面積
                int height = heights[st.pop()];
                int l = st.isEmpty() ? -1 : st.peek();
                int r = i;
                int area = height * (r - l - 1); // 面積寬度是(l,r), 都不包含兩側所以r-l-1
                res = Math.max(res, area);
            }
            st.push(i);
        }
        return res;
    }
}

/* 
高度變小的時候開始計算, 並更新起始高度為此
heights = [2,1,5,6,2,3]
            *
		*   *
	 	*   *
	 	*   *       *
*	 	*	*   *   *
*	*	*	*   *   *
0	1	2	3   4   5   6

for loop:
i=0
s=[0]

i=1
h[0]>h[1], pop0, area=h[0]*(1-(-1)-1)=2*1=2
s=[1]

i=2
s=[2,1]

i=3
s=[3,2,1]

i=4
h[3]>h[4], pop3, area=h[3]*(4-2-1)=6*1=6
h[2]>h[4], pop2, area=h[2]*(4-1-1)=5*2=10
s=[4,1]

i=5
s=[5,4,1]

i=6
h[5]<0, pop5, area=h[5]*(6-4-1)=3*1=3
h[4]<0, pop4, area=h[4]*(6-1-1)=2*4=8
h[1]<0, pop1, area=h[1]*(6-(-1)-1)=1*6=6

res=10

*/