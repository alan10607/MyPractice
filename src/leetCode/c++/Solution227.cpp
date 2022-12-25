//Stack O(n) O(n), n = s.length(), 空間複雜度為遞迴所用最多為O(n)
class Solution227 {
public:
    int calculate(string s) {
        long num = 0, calNum = 0, res = 0;//為了邊界, 但answer is 32-bit int
        char op = '+';
        for(int i=0; i<s.length(); ++i){
            if(s[i] >= '0' && s[i] <= '9')
                num = num * 10 + s[i] - '0';

            if(s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/' || i == s.length() - 1){
                if(op == '+') calNum += num;
                if(op == '-') calNum -= num;
                if(op == '*') calNum *= num;
                if(op == '/') calNum /= num;
                op = s[i];
                num = 0;

                if(s[i] == '+' || s[i] == '-' || i == s.length() - 1){
                    res += calNum;
                    calNum = 0;
                }
            }
        }
        return res;
    }
};