//Backtracking O(k * (9! / (k! * (9 - k)!))) O(n), C9取k = 9! / (k! * (9 - k)!)
class Solution216 {
public:
    vector<vector<int>> res;

    vector<vector<int>> combinationSum3(int k, int n) {
        vector<int> nums;
        backtracking(1, nums, k, n);
        return res;
    }

    void backtracking(int start, vector<int>& nums, int k, int n) {
        if(nums.size() == k && n == 0){
            res.push_back(nums);
            return;
        }
        if(nums.size() > k || n < 0) return;

        for(int i=start; i<=9; i++){
            nums.push_back(i);
            backtracking(i + 1, nums, k, n - i);
            nums.pop_back();
        }
    }
};