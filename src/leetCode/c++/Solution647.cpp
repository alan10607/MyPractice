//Expand O(n^2) O(1)
class Solution647 {
public:
    int countSubstrings(string s) {
        int res = 0;
        for(int i=0; i<s.length(); ++i){
            res += expand(i, i, s);
            res += expand(i, i + 1, s);
        }
        return res;
    }

    int expand(int l, int r, string s){
        int cnt = 0;
        while(l >= 0 && r < s.length() && s[l] == s[r]){
            ++cnt;
            --l;
            ++r;
        }
        return cnt;
    }
};