//2D-DP KP O(n * negSum) O(negSum)
class Solution494 {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int sum = 0;
        for(int num : nums) sum += num;

        if(sum - target < 0 || (sum - target) % 2 != 0) return 0;
        int negSum = (sum - target) / 2;//是否能用num湊出negSum
        vector<int> dp(negSum + 1);
        dp[0] = 1;
        for(int num : nums){
            for(int i = negSum; i - num >= 0; --i){//0-1背包問題, 內層反序
                dp[i] += dp[i - num];
            }
        }
        return dp[negSum];
    }
};
/*
正數和 - 負數和 = target
負數和 = 正數和 - target
2*負數和 = 全數和 - target
負數和 = (全數和 - target) / 2
*/