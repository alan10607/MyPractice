package leetCode.java;

//Greedy O(n) O(1)
class Solution55 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightMost = 0; // 可以到達的右index 
        for (int i = 0; i < n; ++i) {
            if (i > rightMost) {
                return false;
            }
            rightMost = Math.max(rightMost, i + nums[i]);
            if (rightMost >= n - 1) {
                return true;
            }
        }
        return false;
    }
}