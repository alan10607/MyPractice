//LR Pointer O(n^3) O(logn)
class Solution18 {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());

        vector<vector<int>> res;
        for (int i = 0; i < nums.size() - 3; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.size() - 2; ++j) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int l = j + 1;
                int r = nums.size() - 1;
                while (l < r) {
                    if (l > j + 1 && nums[l] == nums[l - 1]) {
                        ++l;
                        continue;
                    }
                    if (r < nums.size() - 1 && nums[r] == nums[r + 1]) {
                        --r;
                        continue;
                    }

                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        res.push_back({nums[i], nums[j], nums[l], nums[r]});
                    }

                    if (sum < target) {
                        ++l;
                    } else {
                        --r;
                    }
                }
            }
        }
        return res;
    }
};


class Solution18_2 {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());

        return nSum(nums, target, 0, 4); // 解決100sum問題
    }

    vector<vector<int>> nSum(vector<int>& nums, long target, int start, int n) { // long target 避免overflow
        if (n == 2) {
            vector<vector<int>> res;
            int l = start, r = nums.size() - 1;
            while (l < r) {
                if (l > start && nums[l] == nums[l - 1]) {
                    ++l;
                    continue;
                }
                if (r < nums.size() - 1 && nums[r] == nums[r + 1]) {
                    --r;
                    continue;
                }

                int sum = nums[l] + nums[r];
                if (sum == target) {
                    res.push_back({nums[l], nums[r]});
                }

                if (sum < target) {
                    ++l;
                } else {
                    --r;
                }
            }
            return res;
        } else {
            vector<vector<int>> res;
            for (int i = start; i < nums.size(); ++i) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                vector<vector<int>> sub = nSum(nums, target - nums[i], i + 1, n - 1);
                for (vector<int> selected : sub) {
                    selected.push_back(nums[i]); // 加入現在這個num
                    res.push_back(selected); // sub的答案集成到res
                }
            }
            return res;
        }
    }
};