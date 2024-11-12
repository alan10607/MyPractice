//Two Pointers O(n) O(1)
class Solution27 {
public:
    int removeElement(vector<int>& nums, int val) {
        int fast = 0, slow = 0;
        while (fast<nums.size()) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow; // 剛好會在下一個寫入位置, 即位置+1
    }
};