package leetCode.java;

//Bit O(n) O(1)
class Solution136 {
    public int singleNumber(int[] nums) {
        int xor = 0;
        for(int num : nums)
            xor ^= num;

        return xor;
    }
}