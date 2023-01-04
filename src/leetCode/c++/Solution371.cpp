//Bit O(log(INT_MAX)) O(1), log(INT_MAX) = 32
class Solution371 {
public:
    int getSum(int a, int b) {
        while (b != 0) {
            int carry = ((unsigned int) (a & b)) << 1;//若為負數會不給左移, 所以位移前先unsigned
            a = a ^ b;
            b = carry;
        }
        return a;
    }
};
/*
不考慮進位的相加: 0+0=0 0+1=1 1+0=1 1+1=0 即xor
只考慮進位的相加: 0+0=0 0+1=0 1+0=1 1+1=1 即and

        0101
+       0001
------------
^       0100
&<<1    0010
------------
^       0110
&<<1    0000
------------
        0110

*/