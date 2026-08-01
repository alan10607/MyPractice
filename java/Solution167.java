package leetCode.java;

//LR Pointer O(n) O(1)
class Solution167 {
    public int[] twoSum(int[] nums, int target) {
        // Input Array Is Sorted, 其實就是3sum的內層算法
        int l = 0, r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                return new int[]{l + 1, r + 1};
            }else if (nums[l] + nums[r] < target) {
                ++l;
            } else {
                --r;
            }
        }

        return new int[]{};
    }
}