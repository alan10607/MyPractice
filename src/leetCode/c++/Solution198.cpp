//DP O(n) O(1)
class Solution198 {
public:
    int rob(vector<int>& nums) {
        int one = nums[0], two = 0;
        for(int i=1; i<nums.size(); ++i){
            int temp = one;
            one = max(one, nums[i] + two);
            two = temp;
        }
        return max(one, two);
    }
};