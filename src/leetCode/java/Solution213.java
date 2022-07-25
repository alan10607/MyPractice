package leetCode.java;

//DP O(n) O(1)
class Solution213 {
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        //拆成 0 ~ n-2 與 1 ~ n-1 兩個區段進行
        return Math.max(canRob(0, nums.length - 2, nums), canRob(1, nums.length - 1, nums));
    }

    public int canRob(int i, int j, int[] nums){
        int one = nums[i];
        int two = 0;
        for(int k = i + 1; k <= j; k++){
            int temp = one;
            one = Math.max(one, two + nums[k]);
            two = temp;
        }
        return one;
    }
}
/* nums = [1,2,3,1] 不考慮頭尾相連的話
dp[0] = nums[0] = 1
dp[1] = max(dp[0], nums[1]) = 2
dp[2] = max(dp[1], dp[0] + nums[2]) = max(2, 1+3) = 4
dp[3] = max(dp[2], dp[1] + nums[3]) = max(4, 2+1) = 4
...
dp[n] = max(dp[n-1], dp[n-2] + nums[n])
*/