package leetCode.java;

//Greedy Kadane's Algorithm O(n) O(1)
class Solution53 {
    public int maxSubArray(int[] nums) {
        int sum = 0, res = Integer.MIN_VALUE;;
        for (int num : nums) {
            sum = Math.max(sum + num, num); // 若num比較大, 則捨去之前加總的數列
            res = Math.max(res, sum);
        }
        return res;
    }
}
/*
sum = 以目前位置結尾的最大 subarray sum
res = 到目前為止最大的答案
num本體更大的話, 直接拋棄之前的

nums=   -2, 1,      -3, 4,      -1, 2,  1,  -5, 4
sum=    -2  -1->1   -2  2->4    3   5   6   1   5


*/