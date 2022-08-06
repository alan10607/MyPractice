package leetCode.java;

//DP O(target * n) O(target), n = nums.length
class Solution377 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i=1 ; i<=target; i++){
            for(int num : nums){
                if(i >= num)
                    dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
/* 可重複, nums = [1,2,3], target = 4
dp[0] = () = 1
dp[1] = (1) = 1
dp[2] = (11 2) = dp[1] + dp[0] = 2
dp[3] = (111 21 12 3) = dp[2] + dp[1] + dp[0] = 4
dp[4] = (1111 211 121 31 112 22 13) = dp[3] + dp[2] + dp[1] = 7
...
dp[n] = dp[n-nums[0]] + dp[n-nums[1]] + ...
*/