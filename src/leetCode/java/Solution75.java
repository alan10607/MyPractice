package leetCode.java;

//LR Pointer O(n) O(1)
class Solution75 {
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1, cur = 0;
        while(cur <= r){
            if(nums[cur] == 0){
                swap(l++, cur++, nums);
            }else if(nums[cur] == 2){
                swap(cur, r--, nums);
            }else{
                cur++;
            }
        }
    }

    public void swap(int a, int b, int[] nums){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}