package leetCode.java;

//Greedy Kadane's Algorithm O(n) O(1)
class Solution152 {
    public int maxProduct(int[] nums) {
        int maxPro = 1, minPro = 1, res = Integer.MIN_VALUE;
        for (int num : nums) {
            int temp = maxPro;
            maxPro = Math.max(num, Math.max(num * maxPro, num * minPro));
            minPro = Math.min(num, Math.min(num * temp, num * minPro));
            res = Math.max(res, maxPro);
        }
        return res;
    }
}
/*
nums = [2,3,-2,4]


/*
考慮有可能負負得正

maxPro = 以目前位置結尾的最大 subarray product 
    = max(nums[i], nums[i]＊maxPro, nums[i]＊minPro)

minPro = 以目前位置結尾的最小 subarray product
    = min(nums[i], nums[i]＊maxPro, nums[i]＊minPro)

res = 到目前為止最大的答案

nums=   2,  3,  -2, 4
maxPro= 2   6   -2  4
minPro= 2   3   -6  -24


res=6

*/