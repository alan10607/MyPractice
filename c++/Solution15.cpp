//LR Pointer O(n^2) O(logn)
class Solution15 {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());//c++要指定範圍

        int n = nums.size();
        vector<vector<int>> res;
        for (int i = 0; i < n; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    ++l;
                    continue;
                }
                if (r < n - 1 && nums[r] == nums[r + 1]) {
                    --r;
                    continue;
                }

                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) res.push_back({nums[i], nums[l], nums[r]});

                if (sum < 0) {
                    ++l;
                } else {//sum > 0 || sum == 0 時都要移動
                    --r;
                }
            }
        }
        return res;
    }
};