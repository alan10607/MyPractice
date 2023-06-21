//Stack O(n) O(num), n = tokens.size(), num = tokens中為數字者
class Solution150 {
public:
    int evalRPN(vector<string>& tokens) {
        stack<long> st;
        for(string token : tokens){
            //token可能是十位數以上, 不要用char判斷
            if(token != "+" && token != "-" && token != "*" && token != "/"){
                st.push(stoi(token));
            }else{
                long a = st.top(); st.pop();
                long b = st.top(); st.pop();
                if(token == "+") st.push(b + a);
                if(token == "-") st.push(b - a);
                if(token == "*") st.push(b * a);
                if(token == "/") st.push(b / a);
            }
        }
        return st.top();
    }
};