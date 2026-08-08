package leetCode.java;

//DP O(n) O(1)
class Solution213 {
    public int rob(int[] nums) {
        // 房子首尾相連, 因此不能同時偷第一間和最後一間, 拆成兩種情況:
        // 1. 偷的範圍 [0, n-2] (不考慮最後一間)
        // 2. 偷的範圍 [1, n-1] (不考慮第一間)
        // 各自套用 198題 House Robber, 取最大值
        
        int n = nums.length; // 1 <= nums.length
        if (n == 1) {
            return nums[0];
        }

        return Math.max(rob(0, n - 2, nums), rob(1, n - 1, nums));
    }

    public int rob(int start, int end, int[] nums) {
        int cur = nums[start], pre = 0;
        for (int i = start + 1; i <= end; ++i) {
            int temp = cur;
            cur = Math.max(cur, pre + nums[i]);
            pre = temp;
        }
        return cur;
    }
}