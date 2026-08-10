//2D-DP O(n^3) O(n^2)
class Solution312 {
public:
    int maxCoins(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> dp(n + 2, vector<int>(n + 2));//dp[i][j]表Burst掉區間[i,j]內所有氣球, 可以得到的最大可能
        nums.insert(nums.begin(), 1);//頭尾加入1方便計算
        nums.push_back(1);
        for(int len = 0; len < n; ++len){//dp[i][j] 依賴任意更短的片段在[i,j]區間, 所以要先算短區間, 再算長區間(Interval DP)
            for(int i = 1; i + len <= n; ++i){
                int j = i + len;/計算區間[i,j]
                for(int k = i; k <= j; ++k){//i <= k <= j, 找max([i]~[k-1] + [i-1]*[k]*[j+1] + [k+1]~[j])
                    dp[i][j] = max(dp[i][j], dp[i][k - 1] + nums[i - 1] * nums[k] * nums[j + 1] + dp[k + 1][j]);
                }
            }
        }
        return dp[1][n];
    }
};
/* nums = [3,1,5,8]
依序求可能的最大值:

長度 1:
[3] [1] [5] [8]

長度 2:
[3,1] [1,5] [5,8]

長度 3:
[3,1,5] [1,5,8]

長度 4:
[3,1,5,8]
*/