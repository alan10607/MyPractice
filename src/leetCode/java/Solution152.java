package leetCode.java;

//DP Kadane's Algorithm O(n) O(1)
class Solution152 {
    public int maxProduct(int[] nums) {
        int max = 1;
        int min = 1;
        int res = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++){
            int temp = max;
            //不能連續故該點的最大或最小值, 可能是這個數本身或是乘上之前帶過來的
            max = Math.max(nums[i], Math.max(max * nums[i], min * nums[i]));
            min = Math.min(nums[i], Math.min(temp * nums[i], min * nums[i]));
            res = Math.max(res, max);
        }
        return res;
    }
}
/*
max = max(num, max*num, min*num)
min = min(num, max*num, min*num)
 */