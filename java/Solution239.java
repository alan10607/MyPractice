package leetCode.java;

import java.util.*;

//Slide Window Stack O(n) O(k), n = nums.length
class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();//<位置1, ...>
        int l = 0;
        int r = 0;
        while(r < nums.length){
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[r])
                deque.pollLast();//移除較小的

            deque.offer(r);//放入佇列

            if(l > deque.peek())
                deque.poll();//移除超出範圍的

            if(r + 1 >= k){//開始記錄
                res[l] = nums[deque.peek()];
                l++;
            }
            r++;
        }
        return res;
    }
}
/* 維護一佇列, 超過位子或較小都刪除
nums[index]   大-------小
index         小-------大
				[deque]


          0  1  2  3  4  5  6  7
nums =  [ 1, 3,-1,-3, 5, 3, 6, 7], k = 3
deque =      1  2
deque =      1  2  3
deque =               4
...
*/