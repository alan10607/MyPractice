package leetCode.java;

//2D-DP KP O(n * nega) O(nega), nega = (sum(nums) - target) / 2
class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        // 轉換成背包問題
        // (負數和) = ( (全數和) - target ) /2
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum - target < 0 || (sum - target) % 2 == 1) { // 記得要判斷sum-target>0
            return 0; // 無法湊出負數和, 不存在
        }

        // 套用0/1背包問題
        int cap = (sum - target) / 2;
        int[] dp = new int[cap + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = cap; i >= num; --i) { // 0-1背包問題, 內層反序
                dp[i] += dp[i - num];
            }
        }

        return dp[cap];
    }
}
/*
(正數和) - (負數和) = target
(負數和) = (正數和) - target
(負數和)*2 = (正數和) - target + (負數和)
(負數和)*2 = (全數和) - target
(負數和) = ( (全數和) - target ) /2

*把 (負數和) 當作target會比較小 因為
(正數和) = ( (全數和) + target) / 2 


ex: nums = [1,1,2,2,3], target = 3
sum=9, cap=(sum-target)/2=(9-3)/2=3
dp[i] 定義: 湊出總和 i, 有幾種方法, dp[0]=1

        <------------- 逆序更新
        0   1   2   3
init    1   0   0   0
1       1   1   0   0
1       1   2   1   0
2       1   2   2   2
2       1   2   3   4
3       1   2   3   5


res=5
*/