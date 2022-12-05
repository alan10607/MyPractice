//Stack O(S + n) O(S + m), S = s.length(), n = 要重複的字串長度, m = 最大的[]層數
class Solution394 {
public:
    string decodeString(string s) {
        stack<string> resSt;
        stack<int> numSt;
        string res = "";
        int num = 0;
        for(int i=0; i<s.length(); ++i){
            if(s[i] >= '0' && s[i] <= '9'){
                num = num * 10 + (s[i] - '0');
            }else if(s[i] == '['){
                resSt.push(res);
                res.clear();
                numSt.push(num);
                num = 0;
            }else if(s[i] == ']'){
                string merge = resSt.top(); resSt.pop();
                int n = numSt.top(); numSt.pop();
                for(int k=0; k<n; ++k)
                    merge += res;

                res = merge;
            }else{
                res.push_back(s[i]);
            }
        }
        return res;
    }
};