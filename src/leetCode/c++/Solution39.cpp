//Backtracking O(n^2) O(target), n = candidates.size()
class Solution39 {
public:
    vector<vector<int>> res;

    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<int> list;
        backtracking(list, 0, candidates, target);
        return res;
    }

    void backtracking(vector<int>& list, int i, vector<int>& candidates, int target){
        if(target == 0){
            res.push_back(list);//vector.push_back會直接複製value到新的address, 不用像Java再new
            return;
        }
        if(i == candidates.size() || target < 0) return;

        backtracking(list, i + 1, candidates, target);

        list.push_back(candidates[i]);
        backtracking(list, i, candidates, target - candidates[i]);
        list.pop_back();
    }
};