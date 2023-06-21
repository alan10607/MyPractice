//Backtracking O(n * n!) O(n)
class Solution77 {
public:
    vector<vector<int>> res;

    vector<vector<int>> combine(int n, int k) {
        vector<int> nums;
        backtracking(1, nums, n, k);
        return res;
    }

    void backtracking(int start, vector<int>& nums, int n, int k){
        if(nums.size() == k){
            res.push_back(nums);
            return;
        }

        for(int i=start; i<=n; ++i){
            nums.push_back(i);
            backtracking(i + 1, nums, n, k);
            nums.pop_back();
        }
    }
};