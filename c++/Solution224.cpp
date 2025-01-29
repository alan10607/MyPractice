//Stack O(n) O(s), n = s.length(), s = st.size()即括號數量
class Solution224 {
public:
    int calculate(string s) {
        int res = 0, num = 0, sign = 1;
        stack<int> st;

        for (int i = 0; i < s.length(); ++i) {
            char ch = s[i];

            if (ch >= '0' && ch <= '9') {
                num = 10 * num + (ch - '0');
            }

            if (ch == '+' || ch == '-' || i == s.length() - 1) { // 最後一個也拉進來算
                res += sign * num;
                num = 0;
                sign = ch == '+' ? 1 : -1;
            }

            if (ch == '(') {
                st.push(res);
                st.push(sign);
                res = 0; // 重設回預設
                num = 0;
                sign = 1;
            }

            if (ch == ')') {
                res += sign * num;
                int last_sign = st.top(); st.pop();
                int last_num = st.top(); st.pop();
                res = last_num + last_sign * res;
                num = 0;
                sign = 1;
            }
        }

        return res;
    }
};


//Backtracking O(n) O(n), n = s.length(), 空間複雜度為遞迴所用最多為O(n)
class Solution224_2 {
public:
    int calculate(string s) {
        int res = 0, num = 0, op = 1;

        for (int i = 0; i < s.length(); ++i) {
            if (s[i] == '(') {
                int start = i, cnt = 0; // 匹配括號
                for (; i < s.length(); ++i) {
                    if (s[i] == '(') ++cnt;
                    if (s[i] == ')') --cnt;
                    if (cnt == 0) break;
                }
                // 只取括弧的內容
                num = calculate(s.substr( start + 1, i - start - 1)); //(i - 1) - (start + 1) + 1
            }

            if (s[i] >= '0' && s[i] <= '9')
                num = 10 * num + (s[i] - '0');

            // 遇到運算符號或最後時, 處理前一個運算
            if (s[i] == '+' || s[i] == '-' || i == s.length() - 1) {
                res += op * num;
                num = 0;
                op = s[i] == '+' ? 1 : -1; // 紀錄給下次用
            }
        }

        return res;
    }
};
