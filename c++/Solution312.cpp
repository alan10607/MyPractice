//2D-DP O(n^3) O(n^2)
class Solution312 {
public:
    int maxCoins(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> dp(n + 2, vector<int>(n + 2));//dp[i][j]表i~j的最大可能
        nums.insert(nums.begin(), 1);//頭尾加入1方便計算
        nums.push_back(1);
        for(int len = 0; len < n; ++len){
            for(int i = 1; i + len <= n; ++i){
                int j = i + len;
                for(int k = i; k <= j; ++k){//i <= k <= j, 找[i]~[k-1] + [i-1]*[k]*[j+1] + [k+1]~[j]
                    dp[i][j] = max(dp[i][j], dp[i][k - 1] + nums[i - 1] * nums[k] * nums[j + 1] + dp[k + 1][j]);
                }
            }
        }
        return dp[1][n];
    }
};
/* nums = [3,1,5,8]
依序求可能的最大值:
3, 1, 5, 8, 3~1, 1~5, 5~8, 3~5, 1~8, 3~8

*/