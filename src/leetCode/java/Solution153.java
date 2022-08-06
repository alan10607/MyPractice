package leetCode.java;

//Binary Search O(logn) O(1)
class Solution153 {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){//不用相等, 否則l = r = mid時會卡在右測不能break
            int mid = (l + r) / 2;
            if(nums[mid] > nums[r]){
                //在左側
                l = mid + 1;
            }else{
                //在右側
                r = mid;//保留最小
            }
        }
        return nums[r];
    }
}