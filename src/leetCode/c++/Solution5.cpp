//Expand O(n^2) O(1)
class Solution5 {
public:
    string longestPalindrome(string s) {
        string res = "";
        for(int i=0; i<s.length(); ++i){
            string odd = expend(i, i, s);
            string even = expend(i, i + 1, s);
            if(odd.length() > res.length()) res = odd;
            if(even.length() > res.length()) res = even;
        }
        return res;
    }

    string expend(int l, int r, string s){
        while(0 <= l && r < s.length() && s[l] == s[r]){
            --l;
            ++r;
        }
        ++l;//抵銷最後一次
        --r;
        return s.substr(l, r - l + 1);
    }
};