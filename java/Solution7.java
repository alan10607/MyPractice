package leetCode.java;

//O(logx(10為底)) O(1)
class Solution7 {
    public int reverse(int x) {
        //not allow to store 64-bit integers
        int reverse = 0;
        while(x != 0){
            int last = x % 10;

            //return 0 if go outside the signed 32-bit integer range
            if( reverse >  Integer.MAX_VALUE / 10
            || (reverse == Integer.MAX_VALUE / 10 && last > Integer.MAX_VALUE % 10)
            ||  reverse <  Integer.MIN_VALUE / 10
            || (reverse == Integer.MIN_VALUE / 10 && last < Integer.MIN_VALUE % 10))
                return 0;

            reverse = reverse * 10 + last;
            x /= 10;
        }
        return reverse;
    }
}