package leetCode.java;

//Bit O(logn) O(1), 因為for迴圈n==0會跳出, 則時間複雜度為以2為底的logn(每次都除以二)
class Solution190 {
    public int reverseBits(int n) {
        // 0 <= n <= 231 - 2, n is even, treat n as an unsigned value
        int res = 0;
        for (int i = 0; i < 32 && n != 0; ++i) { // n為0時直接跳出
            res |= (n & 1) << (31 - i); // 把n的最右邊放到res最左邊
            n >>>= 1; // 準備下一位
        }
        return res;
    }
}