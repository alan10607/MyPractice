package leetCode.java;

//DP O(n) O(1)
class Solution198 {
    public int rob(int[] nums) {
        int one = nums[0];
        int two = 0;
        for(int i=1; i<nums.length; i++){
            int temp = one;
            one = Math.max(one, nums[i] + two);
            two = temp;
        }
        return one;
    }
}
/* nums = [1,2,3,1]
dp[0] = nums[0] = 1
dp[1] = max(dp[0], nums[1]) = 2
dp[2] = max(dp[1], dp[0] + nums[2]) = max(2, 1+3) = 4
dp[3] = max(dp[2], dp[1] + nums[3]) = max(4, 2+1) = 4
...
dp[n] = max(dp[n-1], dp[n-2] + nums[n])
*/