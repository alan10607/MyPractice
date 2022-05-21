package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_Binary {

    //Time Complexity: O(log(Integer.MAX_VALUE)), Space Complexity: O(1)
    //每次運算都能進一位, 如果都進位最多需要log(Integer.MAX_VALUE), 其中位元運算視為原子操作
    class Solution371 {
        public int getSum(int a, int b) {
            while(b != 0){
                int temp = a;
                a = a ^ b;
                b = (temp & b) << 1;
            }
            return a;

        /*
        ex: 9 + 11 = 20

               1001 (9)
          +    1011 (11)
        ---------------
         a^b   0010 (2)
    (a&b)<<1  1001  (18)
        ---------------
         a^b  10000 (16)
    (a&b)<<1  00010 (2)
        ---------------
         a^b  10010 (20)
    (a&b)<<1  00000
        ---------------
              10010

        Java位元運算子:
        ~   位元補數, ~101 = 010
        &   and, 1100 & 1010 = 1000, 兩者為1才為1
        |   or,  1100 & 1010 = 1110, 其中為1就為1
        ^   xor, 1100 & 1010 = 0110, 兩者不同才為1
        <<  保留正負號的左移運算, 101 << 1 = 1010, 兩當於*(2^n)
        >>  保留正負號的右移運算, 1010 >> 1 = 101, 兩當於/(2^n)
        >>> 不保留正負號的右移運算, 11111111111111111111111111110000(-16) >>> 2 = 00111111111111111111111111111100(1073741820)
        */
        }
    }

    //Time Complexity: O(k), Space Complexity: O(1), k = 32(bits)
    public class Solution191 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int count = 0;
            while(n != 0){
                if((n & 1) == 1)
                    count++;
                n = n >>> 1;//要不保留正負號右移
            }
            return count;

            /* 另一種解法, 改成1去左移
            for(int i=0; i<32; i++){
                if((n & (1 << i)) != 0)
                    count++;
            }
            */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution338 {
        public int[] countBits(int n) {
            //透過動態規劃到達O(n)
            int[] res = new int[n + 1];
            int highBit = 0;//最高的有效位1, 2, 4...
            //直接從1開始
            for(int i=1; i<=n; i++){
                //如果只有一個1, 則為1, 2, 4, 8...
                if((i & (i - 1)) == 0)
                    highBit = i;
                res[i] = 1 + res[i - highBit];
            }
            return res;

            /*
            0 = 0000, 0
            1 = 0001, 1 + dp[0] = 1 + dp[i - 1]
            2 = 0010, 1 + dp[0] = 1 + dp[i - 2]
            3 = 0011, 1 + dp[1] = 1 + dp[i - 2]
            4 = 0100, 1 + dp[0] = 1 + dp[i - 4]
            5 = 0101, 1 + dp[1] = 1 + dp[i - 4]
            6 = 0110, 1 + dp[2] = 1 + dp[i - 4]
            7 = 0111, 1 + dp[3] = 1 + dp[i - 4]
            8 = 1000, 1 + dp[0] = 1 + dp[i - 8]
            ...
            */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution268 {
        public int missingNumber(int[] nums) {
            //using only O(1) extra space complexity and O(n)
            //由於相同數字xor始終為0, ex. 5 ^ 5 = 101 ^ 101 = 000
            //複製一個長度為nums.length + 1的數列, 互相xor就可以得出
            int xor = 0;//初始設為0
            for(int i=0; i<nums.length; i++)
                xor ^= nums[i];

            //長度為nums.length + 1的數列
            for(int i=0; i<nums.length + 1; i++)
                xor ^= i;

            return xor;

            /* 另一種解法, 透過數學
            int sum = (0 + nums.length) * (nums.length + 1) / 2;//上底加下底乘高除2
            for(int n : nums)
                sum -= n;
            return sum;
            */
        }
    }

    //Time Complexity: O(logn), Space Complexity: O(1)
    public class Solution190 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int res = 0;
            for(int i = 0; i< 32 && n != 0; i++){
                res |= (n & 1) << (31 - i);//獲得n最右邊的值ㄝ, 丟到res最左邊
                n >>>= 1;//原本的往右滑1
            }
            //int temp = n & 1;
            //res |= temp << (31 - i);
            //n = n >>> 1;
            return res;
        }

        /*  位運算分治, Time Complexity: O(1), Space Complexity: O(1)
            public int reverseBits(int n) {
                int M1 = 0x55555555; // 01010101010101010101010101010101
                int M2 = 0x33333333; // 00110011001100110011001100110011
                int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
                int M8 = 0x00ff00ff; // 00000000111111110000000011111111
                n = n >>> 1 & M1 | (n & M1) << 1;
                n = n >>> 2 & M2 | (n & M2) << 2;
                n = n >>> 4 & M4 | (n & M4) << 4;
                n = n >>> 8 & M8 | (n & M8) << 8;
                return n >>> 16 | n << 16;
            }
         */
    }

}