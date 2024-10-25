//Backtracking O(n * n!) O(n), 時間複雜度要進行(n!)次backtracking, 每次要跑一次for(n)
class Solution46 {
public:
    vector<vector<int>> res;

    vector<vector<int>> permute(vector<int>& nums) {
        unordered_set<int> visited;
        vector<int> selected;
        backtracking(nums, visited, selected);
        return res;
    }

    void backtracking(vector<int>& nums, unordered_set<int>& visited, vector<int>& selected) {
        if (selected.size() == nums.size()) {
            res.push_back(selected);
        }

        for (int num : nums) {
            if (visited.count(num)) continue;

            visited.insert(num);
            selected.push_back(num);
            backtracking(nums, visited, selected);
            visited.erase(num);
            selected.pop_back();
        }
    }
};