// Backtracking O(n * 2^n) O(n)
class Solution78{
public:
    vector<vector<int>> res;

    vector<vector<int>> subsets(vector<int>& nums) {
        vector<int> selected;
        backtracking(0, selected, nums); // 不用sort因為沒有重複的數字
        return res;
    }

    void backtracking(int start, vector<int>& selected, vector<int>& nums) {
        res.push_back(selected);

        for (int i = start; i < nums.size(); ++i) {
            selected.push_back(nums[i]);
            backtracking(i + 1, selected, nums);
            selected.pop_back();
        }
    }
};