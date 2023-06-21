package leetCode.java;

//Bit O(n) O(1), n = 32
class Solution191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            if((n & 1) == 1) count++;
            n >>>= 1;
        }
        return count;
    }
}
