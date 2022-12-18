//LR Pointer O(n) O(1)
class Solution997 {
public:
    vector<int> sortedSquares(vector<int>& nums) {
        int l = 0, r = nums.size() - 1;
        vector<int> res(nums.size());
        for(int i = res.size() - 1; i >= 0; --i){
            if(abs(nums[l]) > abs(nums[r])){//大的放到最後面
                res[i] = nums[l] * nums[l];
                ++l;
            }else{
                res[i] = nums[r] * nums[r];
                --r;
            }
        }
        return res;
    }
};