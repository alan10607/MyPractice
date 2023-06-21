//O(logx(10為底)) O(1)
class Solution7 {
public:
    int reverse(int x) {
        int res = 0;
        while(x != 0){
            int digit = x % 10;

            //If outside signed 32-bit, then return 0.
            if(res > INT_MAX / 10 || (res == INT_MAX && digit >= INT_MAX % 10)
            || res < INT_MIN / 10 || (res == INT_MIN && digit <= INT_MAX % 10))
                return 0;

            res = 10 * res + digit;
            x /= 10;
        }
        return res;
    }
};