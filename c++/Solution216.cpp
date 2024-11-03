//Backtracking O(k * (9! / (k! * (9 - k)!))) O(n), C9取k = 9! / (k! * (9 - k)!)
class Solution216 {
public:
    vector<vector<int>> res;
    
    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> selected;
        backtracking(1, selected, k, n);
        return res;
    }

    void backtracking(int start, vector<int>& selected, int k, int target) {
        if (target == 0 && selected.size() == k) {
            res.push_back(selected);
            return;
        }
        if (target < 0) return;
        if (selected.size() >= k) return;

        for (int i = start; i <= 9; ++i) {
            selected.push_back(i);
            backtracking(i + 1, selected, k, target - i);
            selected.pop_back();
        }
    }
};