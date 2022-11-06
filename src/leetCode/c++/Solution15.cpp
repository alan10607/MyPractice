//LR Pointer O(n^2) O(n)
class Solution15 {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());//c++要指定範圍

        vector<vector<int>> res;
        for(int i=0; i<nums.size() - 2; ++i){
            if(i > 0 && nums[i] == nums[i - 1])
                continue;

            int l = i + 1, r = nums.size() - 1;
            while(l < r){
                if(l > i + 1 && nums[l] == nums[l - 1]){
                    ++l;
                    continue;
                }
                if(r < nums.size() - 1 && nums[r] == nums[r + 1]){
                    --r;
                    continue;
                }

                int sum = nums[i] + nums[l] + nums[r];

                if(sum == 0)
                    res.push_back({nums[i], nums[l], nums[r]});

                if(sum > 0){
                    --r;
                }else{//sum < 0 || sum == 0 時都要移動
                    ++l;
                }
            }
        }
        return res;
    }
};