//Backtracking O(n * n!) O(n), 時間複雜度要進行(n!)次backtracking, 每次要跑一次for(n)
class Solution46 {
public:
    vector<vector<int>> res;

    vector<vector<int>> permute(vector<int>& nums) {
        vector<int> vec;
        vector<bool> visited(nums.size());
        backtracking(vec, visited, nums);
        return res;
    }

    void backtracking(vector<int>& vec, vector<bool>& visited, vector<int>& nums) {
        if(vec.size() == nums.size()){
            res.push_back(vec);
            return;
        }

        for(int i=0; i<nums.size(); ++i){
            if(visited[i]) continue;

            visited[i] = true;
            vec.push_back(nums[i]);
            backtracking(vec, visited, nums);
            vec.pop_back();
            visited[i] = false;
        }
    }
};