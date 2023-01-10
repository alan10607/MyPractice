//DP O(target * n) O(target), n = nums.size()
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
/* nums = [1,2,3], target = 4, 只是計算可重複的組合可能數, 用backtracking太吃記憶體, 用DP即可

dp[0]=1
dp[1]=(1)=1
dp[2]=(11 2)=2
dp[3]=(111 21 12 3)=4
dp[4]=(1111 211 121 31 112 22 13)=7

*/