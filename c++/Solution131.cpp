//Backtracking O(n * n!) O(n), 最差的情況下每個字母可以都是回文, 即需要n!次遞迴
class Solution131 {
public:
    vector<vector<string>> res;
    
    vector<vector<string>> partition(string s) {
        vector<string> strs;
        backtracking(0, strs, s);
        return res;
    }

    void backtracking(int i, vector<string>& strs, string s){
        if(i == s.length()){
            res.push_back(strs);
            return;
        }

        for(int len = 1; i + len <= s.length(); ++len){
            string tmp = s.substr(i, len);
            if(check(tmp)){
                strs.push_back(tmp);
                backtracking(i + len, strs, s);
                strs.pop_back();
            }
        }
    }

    bool check(string s){
        int l = 0, r = s.length() - 1;
        while(l < r){
            if(s[l++] != s[r--]) return false;
        }
        return true;
    }
};
/* s = "aab"
                        []
            a           aa          aab
        a       ab
        b

*/