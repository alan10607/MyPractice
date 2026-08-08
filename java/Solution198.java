package leetCode.java;

//DP O(n) O(1)
class Solution198 {
    public int rob(int[] nums) {
        int cur = nums[0], pre = 0;
        for (int i = 1; i < nums.length; ++i) {
            int temp = cur;
            cur = Math.max(cur, pre + nums[i]);
            pre = temp;
        }
        return cur;
    }
}
/*
ex: nums = [1,2,3,1]

dp[-1] = 0
dp[0] = 1
dp[1] = max(dp[0], dp[-1] + nums[1]) = max(1, 0 + 2) = 2
dp[2] = max(dp[1], dp[0] + nums[2]) = max(2, 1 + 3) = 4
dp[3] = max(dp[2], dp[1] + nums[3]) = max(4, 2 + 1) = 4
...

dp[n] = max(dp[n-1], dp[n-2] + nums[n])
*/