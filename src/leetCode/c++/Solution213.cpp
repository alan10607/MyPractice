//DP O(n) O(1)
class Solution213 {
public:
    int rob(vector<int>& nums) {
        if(nums.size() == 1) return nums[0];
        return max(rob(nums, 0, nums.size() - 2), rob(nums, 1, nums.size() - 1));
    }

    int rob(vector<int>& nums, int start, int end){
        int one = nums[start];
        int two = 0;
        for(int i = start + 1; i <= end; ++i){
            int temp = one;
            one = max(nums[i] + two, one);
            two = temp;
        }
        return max(one, two);
    }
};