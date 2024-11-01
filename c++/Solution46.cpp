//Backtracking O(n * n!) O(n), 時間複雜度要進行(n!)次backtracking, 每次要跑一次for(n)
class Solution46 {
public:
    vector<vector<int>> res;

    vector<vector<int>> permute(vector<int>& nums) {
        vector<int> selected;
        unordered_set<int> visited;
        backtracking(visited, selected, nums);
        return res;
    }

    void backtracking(unordered_set<int>& visited, vector<int>& selected, vector<int>& nums) {
        if (selected.size() == nums.size()) {
            res.push_back(selected);
            return;
        }

        for (int num : nums) {
            if (visited.count(num)) continue;

            visited.insert(num);
            selected.push_back(num);
            backtracking(visited, selected, nums);
            visited.erase(num);
            selected.pop_back();
        }
    }
};