//Backtracking O(n * 2^n) O(n)
class Solution78 {
public:
    vector<vector<int>> res;

    vector<vector<int>> subsets(vector<int>& nums) {
        vector<int> vec;
        backtracking(0, vec, nums);
        return res;
    }

    void backtracking(int i, vector<int>& vec, vector<int>& nums){
        if(i == nums.size()){
            res.push_back(vec);
            return;
        }

        backtracking(i + 1, vec, nums);

        vec.push_back(nums[i]);
        backtracking(i + 1, vec, nums);
        vec.pop_back();
    }
};
/*
                []
        1               []
    12       1      2       []
 123  12   13  1  23  2    3  []

*/