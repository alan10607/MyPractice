//Stack O(n) O(n), n = s.length(), 空間複雜度為遞迴所用最多為O(n)
class Solution227 {
public:
    int calculate(string s) {
        int res = 0, num = 0, cal_num = 0; // cal_num暫時記著先乘除範圍內的解, 就像只有一層的stack
        char op = '+';
        for (int i = 0; i < s.length(); ++i) {
            char ch = s[i];
            if (ch >= '0' && ch <= '9') {
                num = num * 10 + (ch - '0');
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == s.length() - 1) {
                if (op == '+') cal_num += num;
                if (op == '-') cal_num -= num;
                if (op == '*') cal_num *= num;
                if (op == '/') cal_num /= num;
                op = s[i];
                num = 0;

                if (s[i] == '+' || s[i] == '-' || i == s.length() - 1) {
                    res += cal_num; // 把乘除的範圍放入res
                    cal_num = 0;
                }
            }
        }
        return res;
    }
};


//Stack O(n) O(m), n = s.length(), m為st.size()即乘除區間的數量
class Solution227_2 {
public:
    int calculate(string s) {
        int res = 0, num = 0;
        char op = '+';
        stack<int> st; // 依照每個+,-拆開, 裡面放入先乘除後的值
        for (int i = 0; i < s.length(); ++i) {
            char ch = s[i];
            if (ch >= '0' && ch <= '9') {
                num = 10 * num + (ch - '0');
            }

            if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || i == s.length() - 1) {
                if (op == '+') { // 這裡的op是上一個
                    st.push(num);
                } else if (op == '-') {
                    st.push(-num);
                } else if (op == '*') {
                    int last = st.top();
                    st.pop();
                    st.push(last * num);
                } else if (op == '/') {
                    int last = st.top();
                    st.pop();
                    st.push(last / num);
                }
                num = 0;
                op = ch;
            }
        }

        while (!st.empty()) {
            res += st.top();
            st.pop();
        }
        return res;
    }
};