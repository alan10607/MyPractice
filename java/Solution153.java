package leetCode.java;

//Binary Search O(logn) O(1)
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) { // 右界比自己小, 代表在左側
                l = mid + 1; // mid 不會是最小值, 往mid+1移動
            } else { // 右界比自己大或等於, 代表在右側
                r = mid; // mid 可能是最小值, 往mid移動
            }
        }
        return nums[l]; // 回傳val
    }
}