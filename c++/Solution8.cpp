//O(n) O(1)
class Solution8 {
public:
    int myAtoi(string s) {
        //s consists of 'a'~'z', '0'-'9', ' ', '+', '-', '.'
        int i = 0;
        while(s[i] == ' ' && i < s.length()) {
            ++i;
        }

        int sign = 1;
        if(i < s.length() && (s[i] == '+' || s[i] == '-')){
            sign = s[i] == '+' ? 1 : -1;
            ++i;
        }

        int base = 0;
        while(i < s.length() && s[i] >= '0' && s[i] <= '9') {
            int digit = s[i] - '0';
            if(base > INT_MAX / 10 || (base == INT_MAX / 10 && digit > 7)) {//判斷邊界, int32_t: -2147483648 ~ 2147483647
                return sign == 1 ? INT_MAX : INT_MIN;
            }
            base = base * 10 + digit;
            ++i;
        }
        return base * sign;
    }
};
/* 為何只判斷正數時尾數 > 7 就夠?
2147483647  -> 沒進入if
2147483648  -> 進入if, return INT_MAX(2147483647)
-2147483647 -> 沒進入if
-2147483648 -> 進入if, return INT_MIN(-2147483648)

*/



//O(n) O(1)
class Solution8_2 {
public:
    int myAtoi(string s) {
        //s consists of 'a'~'z', '0'-'9', ' ', '+', '-', '.'
        int i = 0;
        while(i < s.length() && s[i] == ' ') ++i;

        int sign = 1;
        if(i < s.length() && (s[i] == '+' || s[i] == '-')){
            sign = s[i] == '+' ? 1 : -1;
            ++i;
        }

        int res = 0;
        while(i < s.length() && s[i] >= '0' && s[i] <= '9'){
            int add = s[i++] - '0';
            add *= sign;//先在這裡計算方便確認範圍

            if(res > INT_MAX / 10 || (res == INT_MAX / 10 && add >= INT_MAX % 10))
                return INT_MAX;

            if(res < INT_MIN / 10 || (res == INT_MIN / 10 && add <= INT_MIN % 10))
                return INT_MIN;

            res = res * 10 + add;
        }

        return res;
    }
};