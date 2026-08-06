package leetCode.java;

//Binary Search O(logn) O(1)
class Solution33 {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) { // 找存在值用<=
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > nums[r]) { //在左側, 只能確定nums[l] ... nums[mid]是遞增, 不確定nums[r]是否最大, 不能使用r判斷大小
                if (nums[l] < target && target < nums[mid]) { // target在左邊, 大-target-mid-最大-小
                    r = mid - 1;
                } else { // target在右邊, 大-mid-target-最大-小 or 大-mid-最大-target-小
                    l = mid + 1;
                }
            } else { //在右側, 只能確定nums[mid] ... nums[r]是遞增, 不確定nums[l]是否最小, 不能使用l判斷大小
                if (nums[mid] < target && target < nums[r]) { // target在右邊, 大-最大-mid-target-小
                    l = mid + 1;
                } else { // target在左邊, 大-最大-target-mid-小 or 大-target-最大-mid-小
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
/* nums = [4,5,6,7,0,1,2], target = 6
判斷nums[mid]落在左側或右側
    1 在左側
        if:   l-target-mid => r = mid - 1
        else: mid-target-r => l = mid + 1
    2 在右側
        if:   mid-target-r => l = mid + 1
        else: l-target-mid => r = mid - 1
*/