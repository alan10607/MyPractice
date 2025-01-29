// Backtracking O(n) O(n), n = s.length(), 空間複雜度為遞迴所用最多為O(n)
class Solution772 { // lintcode849
public:
    int calculate(string s) { // 這題是Solution224 + 227組合
        int res = 0, num = 0, cal_num = 0;
        char op = '+';
        for (int i = 0; i < s.length(); ++i) {
            if (s[i] == '(') {
                int start = i, cnt = 0; // cnt=括號平衡數量
                for (; i < s.length(); ++i) {
                    if (s[i] == '(') ++cnt;
                    if (s[i] == ')') --cnt;
                    if (cnt == 0) break;
                }
                // [start+1, i-1]區間長度=(i-1)-(start+1)+1=i-start-1
                num = calculate(s.substr(start + 1, i - start - 1));
            }

            if (s[i] >= '0' && s[i] <= '9') {
                num = 10 * num + (s[i] - '0');
            }

            if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/' || i == s.length() - 1) {
                switch (op) {
                case '+':
                    cal_num += num;
                    break;
                case '-':
                    cal_num -= num;
                    break;
                case '*':
                    cal_num *= num;
                    break;
                case '/':
                    cal_num /= num;
                    break;
                }
                num = 0;
                op = s[i];

                if (s[i] == '+' || s[i] == '-' || i == s.length() - 1) {
                    res += cal_num; // 放入先乘除的值
                    cal_num = 0;
                }
            }
        }

        return res;
    }
};