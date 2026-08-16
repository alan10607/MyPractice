package leetCode.java;

//O(logx(10為底)) O(1)
class Solution7 {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int digit = x % 10;

            if (res > Integer.MAX_VALUE / 10
                || (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)
                || res < Integer.MIN_VALUE / 10
                || (res == Integer.MIN_VALUE / 10 && digit < Integer.MIN_VALUE % 10)
                ) {
                    return 0;
                }

            res = res * 10 + digit;
            x /= 10;
        }
        return res;
    }
}
/*
Integer.MAX_VALUE =  2147483647
Integer.MIN_VALUE = -2147483648

以下情況出界(不過實際上reverse前的數可能並不存在):
res >   214748364
res ==  214748364 && digit > 7
res <  -214748364
res == -214748364 && digit < -8

*/