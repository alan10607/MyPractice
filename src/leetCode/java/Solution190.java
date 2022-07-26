package leetCode.java;

//Bit O(logn) O(1), 因為for迴圈n==0會跳出, 則時間複雜度為以2為底的logn(每次都除以二)
class Solution190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int reverse = 0;
        for(int i=0; i < 32 && n != 0; i++){//n為0時直接跳出
            reverse |= ((n & 1) << (31 - i));
            n >>>= 1;
        }
        return reverse;
    }
}