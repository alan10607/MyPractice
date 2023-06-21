package leetCode.java;

import java.util.*;

//Stack O(n) O(n)
class Solution84 {
    public int largestRectangleArea(int[] heights) {
        Deque<int[]> deque = new LinkedList<>();//<[位置, 高度], ...>
        int res = 0;
        for(int i=0; i<heights.length; i++){
            int index = i;
            while(!deque.isEmpty() && deque.peek()[1] > heights[i]){
                int[] rect = deque.poll();
                res = Math.max(res, (i - rect[0]) * rect[1]);
                index = rect[0];//更新為可以到的左側
            }
            deque.push(new int[]{index, heights[i]});
        }

        while(!deque.isEmpty()){
            int[] rect = deque.poll();
            res = Math.max(res, (heights.length - rect[0]) * rect[1]);
        }

        return res;
    }
}
/* 高度變小的時候開始計算, 並更新起始高度為此
heights = [1,5,6,2]
		*
	*	*
	*	*
	*	*
	*	*	*
*	*	*	*
0	1	2	3
			^
heights=3時進while
(3-2)*6=6
(3-1)*5=10

更新

	*	*	*
*	*	*	*
0	1	2	3
				^
跑完進最後while
(4-1)*2=6
(4-1)*1=4
*/