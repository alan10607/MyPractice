//Two Pointers O(n) O(1)
class Solution283 {
public:
    void moveZeroes(vector<int>& nums) {
        int index = 0;
        for(int i=0; i<nums.size(); ++i)
            if(nums[i] != 0) nums[index++] = nums[i];

        for(int i=index; i<nums.size(); ++i)
            nums[i] = 0;
    }
};