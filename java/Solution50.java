package leetCode.java;

//Backtracking O(logn) O(logn)
class Solution50 {
    public double myPow(double x, int n) {
        if(x == 1) return x;

        if(n > 1){
            return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
        }else if(n == 1){
            return x;
        }else if(n == 0){
            return 1;
        }else{//n < 0
            //直接先n/2, 避免n=-2^31在-n時溢位
            return n % 2 == 0 ? myPow((1 / x) * (1 / x), - (n / 2)) : (1 / x) * myPow((1 / x) * (1 / x), - (n / 2));
        }
    }
}