package leetCode.java;

//Bit O(log(Integer.MAX_VALUE)) O(1), log(Integer.MAX_VALUE) = 32
class Solution371 {
    public int getSum(int a, int b) {
        //sum without using the operators + and -
        while(b != 0){
            int temp = a;
            a = a ^ b;
            b = (temp & b) << 1;
        }
        return a;
    }
}
/*
        0101
+       0001
------------
xor 	0100
and <  0001
------------
xor 	0110
and <  0000
------------
        1000
*/