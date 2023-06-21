//Backtracking O(n * 2^n) O(n)
class Solution78 {
public:
    vector<vector<int>> res;

    vector<vector<int>> subsets(vector<int>& nums) {
        vector<int> vec;
        backtracking(0, vec, nums);//不用sort因為沒有重複的數字
        return res;
    }

    void backtracking(int start, vector<int>& vec, vector<int>& nums) {
        res.push_back(vec);

        for(int i=start; i<nums.size(); ++i){
            vec.push_back(nums[i]);
            backtracking(i + 1, vec, nums);
            vec.pop_back();
        }
    }
};