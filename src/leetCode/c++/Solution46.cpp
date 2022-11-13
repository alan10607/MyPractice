//Backtracking O(n! * n) O(n), 時間複雜度要進行(n!)次backtracking, 每次要跑一次for(n)
class Solution46 {
public:
    vector<vector<int>> res;

    vector<vector<int>> permute(vector<int>& nums) {
        vector<bool> visited(nums.size());
        vector<int> out;
        backtracking(visited, out, nums);
        return res;
    }

    void backtracking(vector<bool>& visited, vector<int>& out, vector<int>& nums){
        if(out.size() == nums.size()){
            res.push_back(out);
            return;
        }

        for(int i=0; i<nums.size(); ++i){
            if(visited[i]) continue;

            visited[i] = true;
            out.push_back(nums[i]);
            backtracking(visited, out, nums);
            out.pop_back();
            visited[i] = false;
        }
    }
};