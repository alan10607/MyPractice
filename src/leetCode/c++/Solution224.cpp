//Backtracking O(n) O(n), n = s.length(), 空間複雜度為遞迴所用最多為O(n)
class Solution224 {
public:
    int calculate(string s) {
        int res = 0, num = 0, op = 1;

        for(int i=0; i<s.length(); ++i){
            if(s[i] == '('){
                int start = i, cnt = 0;//匹配括號
                for(; i<s.length(); ++i){
                    if(s[i] == '(') ++cnt;
                    if(s[i] == ')') --cnt;
                    if(cnt == 0) break;
                }
                //只取括弧的內容
                num = calculate(s.substr(start + 1, i - start - 1));//(i - 1) - (start + 1) + 1
            }

            if(s[i] >= '0' && s[i] <= '9')
                num = 10 * num + (s[i]  - '0');

            //遇到運算符號或最後時, 處理前一個運算
            if(s[i] == '+' || s[i] == '-' || i == s.length() - 1){
                res += op * num;
                num = 0;
                op = s[i] == '+' ? 1 : -1;//紀錄給下次用

            }
        }

        return res;
    }
};