package leetCode.java;

//Bit O(n) O(1)
class Solution268 {
    public int missingNumber(int[] nums) {
        //using only O(1) extra space complexity and O(n)
        int res = 0;
        for (int i = 0; i <= nums.length; ++i) {
            res ^= i;
        }
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}