package leetCode.java;

import java.util.*;

//Slide Window Queue O(n) O(k), n = nums.length
class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // Monotonic deque單調列隊, 放index, 保持nums[index]大到小
        // Deque 的順序同時代表兩件事:
        // 1. 數值大小順序 (大 -> 小)
        // 2. index 新舊順序 (舊 -> 新)

        Deque<Integer> dq = new ArrayDeque<>(); // 放index
        int l = 0, r = 0, n = nums.length;
        int[] res = new int[n - k + 1];
        while (r < n) {
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[r]) { // 從後面把比較小的抓走
                dq.pollLast();
            }
            dq.offerLast(r);

            if (l > dq.peekFirst()) {
                dq.pollFirst();
            }

            if (r + 1 >= k) {
                res[l] = nums[dq.peekFirst()];
                ++l;
            }
            ++r;
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