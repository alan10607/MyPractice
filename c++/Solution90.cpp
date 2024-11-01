//Backtracking O(n * 2^n) O(n)
class Solution90 {
public:
    vector<vector<int>> res;

    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        vector<int> selected;
        sort(nums.begin(), nums.end()); // 小排到大, 從小開始入列避免重複(此處的重複是指重複使用已的index), 用來判斷跟前一個數是否一樣
        backtracking(0, selected, nums);
        return res;
    }

    void backtracking(int start, vector<int>& selected, vector<int>& nums) {
        res.push_back(selected);

        for (int i = start; i < nums.size(); ++i) {
            if (i > start && nums[i] == nums[i - 1]) continue; // 避免重複, 在遞迴時已經加入了

            selected.push_back(nums[i]);
            backtracking(i + 1, selected, nums);
            selected.pop_back();
        }
    }
};