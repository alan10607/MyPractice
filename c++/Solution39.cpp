//Knapsack Backtracking O(n * 2^n) O(target), n = candidates.size()
class Solution39 {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<int> nums;
        vector<vector<int>> res;
        backtracking(0, target, candidates, nums, res);
        return res;
    }

    void backtracking(int start, int target, vector<int>& candidates, vector<int>& nums, vector<vector<int>>& res){
        if(target == 0) {
            res.push_back(nums);
            return;
        }
        if(target < 0) return;

        for(int i = start; i < candidates.size(); ++i){ // All candidates are distinct, 所以不用判斷前一個是否相同
            nums.push_back(candidates[i]);
            backtracking(i, target - candidates[i], candidates, nums, res);
            nums.pop_back();
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