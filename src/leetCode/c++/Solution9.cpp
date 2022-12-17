//O(logx(10為底)) O(1)
class Solution9 {
public:
    bool isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) return false;//避免10的情況

        int reverse = 0;
        while(x > reverse){
            reverse = reverse * 10 + x % 10;
            x /= 10;
        }
        return x == reverse || x == reverse / 10;
    }
};
/*
        x   reverse
12321   12  123
1221    12  12

*/