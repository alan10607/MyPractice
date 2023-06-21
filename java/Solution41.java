package leetCode.java;

//41 O(n) O(1)
class Solution41 {
    public int firstMissingPositive(int[] nums) {
        for(int i=0; i<nums.length; i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){
                int temp = nums[nums[i] - 1];//如果設int temp = nums[i];的話置換會有問題
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for(int i=0; i<nums.length; i++){
            if(nums[i] != i + 1) return i + 1;
        }

        return nums.length + 1;
    }
}