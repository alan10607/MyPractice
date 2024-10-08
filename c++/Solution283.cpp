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


//Two Pointers O(n) O(1)
class Solution283_2 {
public:
    void moveZeroes(vector<int>& nums) {
        int fast = 0, slow = 0;
        while (fast < nums.size()) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }

        while (slow < nums.size()) {
            nums[slow] = 0;
            ++slow;
        }
    }
};