//Knapsack Backtracking O(n * 2^n) O(target), n = candidates.size()
class Solution40 {
public:
    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<int> nums;
        vector<vector<int>> res;
        sort(candidates.begin(), candidates.end()); // 要避免重複, 所以排序小的先入列篩選
        backtracking(0, target, candidates, nums, res);
        return res;
    }

    void backtracking(int start, int target, vector<int>& candidates, vector<int>& nums, vector<vector<int>>& res) {
        if (target == 0) {
            res.push_back(nums);
            return;
        }
        if (target < 0) return;

        for (int i = start; i < candidates.size(); ++i) {
            if (i > start && candidates[i] == candidates[i - 1])
                continue; // 跳過重複的, 因為該項已經在前一個情況出現過, 即只挑選candidates[i]的那個情況

            nums.push_back(candidates[i]);
            backtracking(i + 1, target - candidates[i], candidates, nums, res); // 不重複所以i+1找下一個
            nums.pop_back();
        }
    }
};
/* sort candidates = [1,2,2,2,5], target = 5, sort
_ -> 1 -> 12 -> 122(o)
                122(skip)
                125(x)
          12(skip, 如果繼續做, 相當於上上面的那個122(skip))
          12(skip)
          15(x)
     2 -> 22 -> 222(x)
                225(x)
          22(skip)
          25(x)
     2(skip)
     2(skip)
     5(0)

res=[[1,2,2],[5]]
*/