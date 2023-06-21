package leetCode.java;

//DP NP-Complete O(n^2) O(n), O(n^2) = O(n * (target * 2)), 其實target就是n的一半
class Solution416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums)
            sum += num;

        if(sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];//是否能在nums中取出target
        dp[0] = true;
        for(int num : nums){
            for(int i=target; i - num >= 0; i--){
                dp[i] |= dp[i - num];
            }
        }
        return dp[target];
    }
}
/* nums = [1,5,11,5], sum(nums)/2=11

	0	1	2	3	4	5	6	7	8	9	10	11
	1
1	1	1
5	1	1				1	1
11	1	1				1	1					1
5	1	1				1	1				1	1
*/