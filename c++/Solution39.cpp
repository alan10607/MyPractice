//Backtracking O(n * 2^n) O(target), n = candidates.size()
class Solution39 {
public:
    vector<vector<int>> res;

    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<int> nums;
        backtracking(0, nums, candidates, target);
        return res;
    }

    void backtracking(int start, vector<int>& nums, vector<int>& candidates, int target) {
        if(target == 0){
            res.push_back(nums);
            return;
        }
        if(target < 0) return;

        for(int i=start; i<candidates.size(); ++i){
            nums.push_back(candidates[i]);
            backtracking(i, nums, candidates, target - candidates[i]);
            nums.pop_back();
        }
    }
};