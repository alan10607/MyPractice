//Backtracking Knapsack O(n * 2^n) O(target), n = candidates.size()
class Solution39 {
public:
    vector<vector<int>> res;

    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<int> selected;
        backtracking(0, selected, candidates, target);
        return res;
    }

    void backtracking(int start, vector<int>& selected, vector<int>& nums, int target) {
        if (target == 0) {
            res.push_back(selected);
            return;
        }
        if (target < 0) return;

        for (int i = start; i < nums.size(); ++i) { // 元素不相同, 所以不用判斷前一個是否相同
            selected.push_back(nums[i]);
            backtracking(i, selected, nums, target - nums[i]); // 從i繼續試探
            selected.pop_back();
        }
    }
};
/* candidates = [2,3,5], target = 8
_ -> 2 -> 22 -> 222 -> 2222(o)
                    -> 2223(x)
                    -> 2225(x)
             -> 223 -> 2233(x)
                    -> 2235(x)
             -> 225 -> 2255(x)
          23 -> 233(o)
             -> 235(x)
          25 -> 255(x)
     3 -> 33 -> 333(x)
             -> 335(x)
       -> 35(o)
     5 -> 55(x)

res=[[2,2,2,2],[2,3,3],[3,5]]
*/