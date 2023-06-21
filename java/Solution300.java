package leetCode.java;

//DP O(n^2) O(n)
class Solution300 {
    public int lengthOfLIS(int[] nums) {
        int res = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for(int i=1; i<nums.length; i++){
            dp[i] = 1;//至少有1, 自己本身
            for(int j=0; j<i; j++){
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
/* nums = [1,2,4,3]
LIS[0] = 1, 至少都會有1即自己本身
LIS[1] = max(1, LIS[0]+1) = 2
LIS[2] = max(1, LIS[0]+1, LIS[1]+1) = 3
LIS[3] = max(1, LIS[0]+1, LIS[1]+1) = 3, 沒有LIS[2]因為nums[3] < nums[2]
...
LIS[n] = max(1, LIS[0]+1, ...)
*/