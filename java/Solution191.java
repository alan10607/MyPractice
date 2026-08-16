package leetCode.java;

//Bit O(n) O(1), n = 32
class Solution191 {
    public int hammingWeight(int n) {
        // you need to treat n as an unsigned value
        int res = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                ++res;
            }
            n >>>= 1;
        }
        return res;
    }
}