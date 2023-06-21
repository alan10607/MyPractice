//DP O(n^2) O(n)
class Solution300 {
public:
    int lengthOfLIS(vector<int>& nums) {
        vector<int> dp(nums.size(), 1);
        int res = 1;
        for(int i=1; i<nums.size(); ++i){
            for(int j=0; j<i; ++j){
                if(nums[i] > nums[j])
                    dp[i] = max(dp[i], dp[j] + 1);
            }
            res = max(res, dp[i]);
        }
        return res;
    }
};
/* nums = [0,1,0,3,2,3]
0   1   0   3   2   3
1   2   1   3   3   4

*/