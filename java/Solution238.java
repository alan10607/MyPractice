package leetCode.java;

//O(n) O(1)
class Solution238 {
    public int[] productExceptSelf(int[] nums) {
        // must run in O(n), 典型的 prefix product + suffix product (前綴積 + 後綴積)題型
        int[] res = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = prefix;
            prefix *= nums[i]; // 過了之後才乘
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= suffix;
            suffix *= nums[i]; // 過了之後才乘
        }

        return res;
    }
}