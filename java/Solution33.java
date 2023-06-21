package leetCode.java;

//Binary Search O(logn) O(1)
class Solution33 {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while(l <= r){
            int mid = (l + r) / 2;
            if(nums[mid] == target) return mid;

            if(nums[mid] > nums[r]){
                //在左側, 只能確定nums[l] ... nums[mid]是遞增, 不確定nums[r]是否最大
                if(nums[l] <= target && target < nums[mid]){
                    r = mid - 1;//l-target-mid-r
                }else{
                    l = mid + 1;//l-mid-target-r
                }
            }else{
                //在右側, 只能確定nums[mid] ... nums[r]是遞增, 不確定nums[l]是否最小
                if(nums[mid] < target && target <= nums[r]){
                    l = mid + 1;;//l-mid-target-r
                }else{
                    r = mid - 1;//l-target-mid-r
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