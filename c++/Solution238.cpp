//O(n) O(1)
class Solution238 {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        vector<int> res(nums.size());
        int temp = 1;
        for(int i=0; i<nums.size(); ++i){
            res[i] = temp;
            temp *= nums[i];
        }

        temp = 1;
        for(int i=nums.size()-1; i>=0; --i){
            res[i] *= temp;
            temp *= nums[i];
        }
        return res;
    }
};
/*
1  2  3  4
1  1  2  6
24 12 4  1

24 12 8  6
*/