//DP NumArray(): O(n) O(n), sumRange(): O(1) O(n)
class NumArray {//Solution303
public:
    vector<int> dp;
    NumArray(vector<int>& nums) {
        dp.resize(nums.size() + 1, 0); // 需要resize, 長度為nums.size() + 1
        for (int i = 0; i < nums.size(); ++i) {
            dp[i + 1] = nums[i] + dp[i];
        }
    }

    int sumRange(int left, int right) {
        return dp[right + 1] - dp[left];
    }
};
/*
index   0     1     2     3
nums=   1     2     3     4
           \     \     \     \
sum=    0 <-- 1 <-- 3 <-- 6 <-- 10

*/