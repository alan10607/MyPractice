package leetCode.java;

//2D-DP O(n * nega) O(nega), nega = (sum(nums) - target) / 2
class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num : nums)
            sum += num;

        int sumSubTarget = sum - target;
        if(sumSubTarget % 2 != 0 || sumSubTarget < 0) return 0;//檢查是否存在sum(負)
        int nega = sumSubTarget / 2;//sum(負)

        int[] dp = new int[nega + 1];//問題轉化為, 是否能從nums中取出nega
        dp[0] = 1;//不取nums可以滿足
        for(int num : nums){
            for(int i=nega; i - num >= 0; i--){//逆向
                dp[i] += dp[i - num];
            }
        }

        return dp[nega];
    }
}
/*
sum(正) - sum(負) = target
sum(負) = sum(正) - target
2 * sum(負) = sum(all) - target

平移nums[i]的距離
nums = [1,2,1], target = 2
                                nega
                                v
                0	1	2	3	4
        0		1	0	0	0	0
        n[0]=1	1	1	0	0	0
        n[1]=2	1	1	1	1	0
  nums> n[2]=1	1	2	2	2	1
*/