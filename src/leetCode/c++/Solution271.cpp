//encode(): O(n) O(n), decode(): O(n) O(1)
class Solution271 {//lintcode659
public:
    string encode(vector<string> &strs) {
        string res = "";
        for(string str : strs)
            res += to_string(str.length()) + '$' + str;

        return res;
    }

    vector<string> decode(string &str) {
        vector<string> res;
        int len = 0;
        for(int i=0; i<str.length(); ++i){
            if(str[i] == '$'){
                string s = str.substr(i + 1, len);
                res.push_back(s);
                i += len;
                len = 0;
            }else{
                len = len * 10 + str[i] - '0';
            }
        }
        return res;
    }
};