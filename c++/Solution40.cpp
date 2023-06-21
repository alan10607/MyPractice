//Backtracking O(n * 2^n) O(target), n = candidates.size()
class Solution40 {
public:
    vector<vector<int>> res;

    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        vector<int> nums;
        sort(candidates.begin(), candidates.end());//要避免重複, 所以排序小的先入列篩選
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
            if(i > start && candidates[i] == candidates[i - 1])
                continue;//跳過重複的, 因為已經在遞歸時吃到

            nums.push_back(candidates[i]);
            backtracking(i + 1, nums, candidates, target - candidates[i]);
            nums.pop_back();
        }

    }
};