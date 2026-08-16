package leetCode.java;

//Bit O(log(Integer.MAX_VALUE)) O(1), log(Integer.MAX_VALUE) = 32
class Solution371 {
    public int getSum(int a, int b) {
        // Without using the operators + and -
        while (b != 0) { // 不能是b>0, 有可能是負數
            int carry = (a & b) << 1;
            a = (a ^ b);
            b = carry;
        }
        return a;
    }
}
/*
Binary addition using XOR and AND:
1. XOR: 計算 不考慮進位 的加法
2. AND + 左移: 計算進位

ex: 5 + 3

a           0101  (5)
b           0011  (3)
----------------
a^b         0110  (6)
(a&b)<<1   0001   (2)
----------------
a^b         0100  (4)
(a&b)<<1   0010   (4)
----------------
a^b         0000  (0)
(a&b)<<1   0100   (8)
----------------
a^b         1000  (8)
(a&b)<<1   0000   (0)

res=8

ex2: -1+1
-1 = 11111111...1111
 1 = 00000000...0001

-1^1=11111111...1110=-2
(-1&1)<<1=00000000...0010=2

carry 會一路左移
2147483648=10000...(但2147483648超出int正數範圍, 實際上=-2147483648)
-2147483648=10000...

-2147483648^-2147483648=0
(-2147483648&-2147483648)<<1=0
*/