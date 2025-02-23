//Backtracking O(n * 2^n) O(2^n), n = nums.size(), 共有2^n個狀態, 要進行n次
class Solution698 {
public:
    unordered_map<int, bool> dp;
    bool canPartitionKSubsets(vector<int>& nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) return false;
        return backtracking(0, 0, 0, sum / k, k, nums);
    }

    bool backtracking(int start, int visited, int sum, int target, int k, vector<int>& nums) {
        if (dp.count(visited)) return dp[visited]; // 剪枝已有該狀態就不用再計算
        if (k == 1) return true;
        if (sum > target) return false; // nums必為正, 所以超過就不用繼續了
        if (sum == target) {
            bool res = backtracking(0, visited, 0, target, k - 1, nums); // 繼續下一bucket
            dp[visited] = res; // 裝滿bucket的時候, 紀錄該結果, 要裝滿bucket再紀錄, 否則剪枝錯
            return res;
        }
        for (int i = start; i < nums.size(); ++i) {
            if (((visited >> i) & 1) == 1) continue;

            visited |= (1 << i); // 第i位數表示nums[i]是否已經visited

            if (backtracking(i, visited, sum + nums[i], target, k, nums)) {
                cout << visited << "!!!!!!!!!";
                return true;
            }

            visited ^= (1 << i); // 透過xor撤銷
        }
        return false;
    }
};


//Backtracking O(n * 2^n) O(2^n), n = nums.size(), 共有2^n個狀態, 要進行n次
class Solution698_2 { // 這個方法現在會TLE
public:
    bool canPartitionKSubsets(vector<int>& nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false; // 無法整除提早返回
        }

        sort(nums.begin(), nums.end(), greater<int>()); // 大排到小, 這樣可以更快判斷bucket是否超過target
        vector<bool> visited(nums.size());
        return backtracking(0, 0, sum / k, k, nums, visited);
    }

    bool backtracking(int start, int sum, int target, int k, vector<int>& nums, vector<bool> visited) {
        if (k == 1) return true;
        if (sum > target) return false; // nums必為正, 所以超過就不用繼續了
        if (sum == target) return backtracking(0, 0, target, k - 1, nums, visited);

        for (int i = start; i < nums.size(); ++i) {
            if (visited[i]) continue;

            visited[i] = true;
            if (backtracking(start + 1, sum + nums[i], target, k, nums, visited)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }
};