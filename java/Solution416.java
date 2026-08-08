package leetCode.java;

//DP Knapsack O(n^2) O(n), O(n^2) = O(n * (target * 2)), 其實target就是n的一半
class Solution416 {
    public boolean canPartition(int[] nums) {
        // 轉成0/1 背包問題
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false; // 無法整除不可能評分
        }

        int target = sum / 2; // 背包容量
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; --i) { // 0/1 背包問題 逆向
                dp[i] |= dp[i - num];
            }
        }
        return dp[target];
    }
}
/* nums = [1,5,11,5], sum(nums)/2=11

	0	1	2	3	4	5	6	7	8	9	10	11
	T
1	T	T
5	T	T				T	T
11	T	1				T	T					T
5	T	T				T	T				T	T
*/