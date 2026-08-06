package leetCode.java;

//Binary Search O(logn) O(1)
class Solution704 {
    public int search(int[] nums, int target) {
        //nums sorted in ascending order
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else { // num[mid] > target
                r = mid - 1;
            }
        }
        return -1;
    }
}