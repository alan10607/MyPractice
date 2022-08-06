package leetCode.java;

//Greedy Kadane's Algorithm O(n) O(1)
class Solution53 {
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        for(int num : nums){
            sum = Math.max(sum + num, num);//若num比較大, 則捨去之前加總的數列
            res = Math.max(res, sum);
        }
        return res;
    }
}