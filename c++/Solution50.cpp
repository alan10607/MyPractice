//Backtracking O(logn) O(logn)
class Solution50 {
public:
    double myPow(double x, int n) {
        if(x == 1 || n == 0){
            return 1;
        }else if(n == 1){
            return x;
        }else if(n > 0){
            return n % 2 == 0 ? myPow(x * x, n / 2) : myPow(x * x, n / 2) * x;
        }else{//n < 0
            return n % 2 == 0 ? myPow((1 / x) * (1 / x), - (n / 2)) : myPow((1 / x) * (1 / x), - (n / 2)) * (1 / x);
        }
    }
};