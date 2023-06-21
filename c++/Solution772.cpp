//Backtracking O(n) O(n), n = s.length(), 空間複雜度為遞迴所用最多為O(n)
class Solution772 {//lintcode849
public:
    int calculate(string s) {
        int res = 0, num = 0, calNum = 0;
        char op = '+';
        for(int i=0; i<s.length(); ++i){
            if(s[i] == '('){
                int start = i, cnt = 0;
                for(; i<s.length(); ++i){
                    if(s[i] == '(') ++cnt;
                    if(s[i] == ')') --cnt;
                    if(cnt == 0) break;
                }
                num = calculate(s.substr(start + 1, i - start - 1));//(i - 1) - (start + 1) + 1
            }

            if(s[i] >= '0' && s[i] <= '9')
                num = 10 * num + (s[i] - '0');

            if(s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/' || i == s.length() - 1){
                switch(op){
                    case '+': calNum += num; break;
                    case '-': calNum -= num; break;
                    case '*': calNum *= num; break;
                    case '/': calNum /= num; break;
                }
                num = 0;
                op = s[i];

                if(s[i] == '+' || s[i] == '-' || i == s.length() - 1){
                    res += calNum;//放入先乘除的值
                    calNum = 0;
                }
            }
        }

        return res;
    }
};