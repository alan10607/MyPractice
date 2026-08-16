package leetCode.java;

//Backtracking O(logn) O(logn)
class Solution50 {
    public double myPow(double x, int n) {
        if (x == 1.0 || n == 0) {
            return 1.0;
        } else if (n == 1) {
            return x;
        } else if (n > 0) {
            return (n % 2 == 0)
                    ? myPow(x * x, n / 2)
                    : myPow(x * x, n / 2) * x;
        } else { // n < 0
            return (n % 2 == 0)
                    ? myPow((1 / x) * (1 / x), -(n / 2)) // 直接先n/2, 避免n=-2^31在-n時溢位
                    : myPow((1 / x) * (1 / x), -(n / 2)) * (1 / x);
        }
    }
}


//O(logn) O(logn)
class Solution50_2 {
    public double myPow(double x, int n) {
        // 迴圈的解法
        long longN = n; // 避免n=-2^31在-n時溢位
        if (longN < 0) {
            x = 1 / x;
            longN = -longN;
        }

        double res = 1.0, product = x;
        while (longN > 0) {
            if ((longN & 1) == 1) { // 尾數是1, 即奇數, 最後一定會進來這邊
                res *= product;
            }
            product *= product;
            longN >>= 1; // 相當於除以2
        }
        return res;
    }
}