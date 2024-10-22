//Binary Search O(logn) O(1)
class Solution34 {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        int start = leftBound(nums, target);
        if (start >= nums.size() || nums[start] != target)
            return {-1, -1};

        int end = leftBound(nums, target + 1) - 1; // 如果target存在, 直接找target+1的leftBound - 1就是右邊界
        return {start, end};
    }

    int leftBound(vector<int>& nums, int target) {
        int l = 0, r = nums.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else { // nums[mid] => target
                r = mid;
            }
        }
        return l;
    }
};