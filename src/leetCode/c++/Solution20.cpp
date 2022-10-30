//Stack O(n) O(n + Z), n = s.length(), Z為括號種類字符集
class Solution20 {
public:
    bool isValid(string s) {
        unordered_map<int, int> bar = {{')', '('}, {']', '['}, {'}', '{'}};
        stack<char> stack;
        for(int i=0; i<s.length(); ++i){
            if(bar.count(s[i])){
                if(!stack.empty() && bar[s[i]] == stack.top()){
                    stack.pop();//沒配對到成功就是失敗, ex: {(})
                }else{
                    return false;
                }
            }else{
                stack.push(s[i]);
            }
        }

        return stack.empty();
    }
};