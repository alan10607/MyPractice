//DP O(target * n) O(target), n = nums.length
class Solution377 {
public:
    int combinationSum4(vector<int>& nums, int target) {
        vector<unsigned int> dp(target + 1);//answer fit in 32-bit, 但是計算會超過, 所以用unsigned
        dp[0] = 1;
        for(int i=1; i<=target; ++i){
            for(int num : nums){
                if(i >= num)
                    dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
};