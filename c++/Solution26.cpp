//Two Pointers O(n) O(1)
class Solution26 {
public:
    int removeDuplicates(vector<int>& nums) {
        int fast = 0, slow = 0;
        while (fast < nums.size()) {
            if (nums[fast] != nums[slow]) {
                ++slow; // 先往前到下一個寫入
                nums[slow] = nums[fast];
            }
            ++fast;
        }

        return slow + 1; // 數量為位置+1
    }
};