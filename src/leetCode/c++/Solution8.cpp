//O(n) O(1)
class Solution8 {
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