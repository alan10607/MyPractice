package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
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
        >>  保留正負號的右移運算, 101 >> 1 = 1010, 兩當於*(2^n)
        <<  保留正負號的左移運算, 1010 << 1 = 101, 兩當於/(2^n)
        >>> 不保留正負號的右移運算
            , 11111111111111111111111111110000(-16) >>> 2 = 00111111111111111111111111111100(1073741820)
        */
        }
    }

}