package leetCode.java;

//LR Pointer O(n) O(1)
class Solution997 {
    public int[] sortedSquares(int[] nums) {
        int l = 0, r = nums.length - 1;
        int[] res = new int[nums.length];
        for(int i = nums.length - 1; i >= 0; i--){
            if(Math.abs(nums[l]) > Math.abs(nums[r])){
                res[i] = nums[l] * nums[l];
                l++;
            }else{
                res[i] = nums[r] * nums[r];
                r--;
            }
        }
        return res;
    }
}