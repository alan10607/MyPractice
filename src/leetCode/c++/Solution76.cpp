//Slide Window O((s + t) * Z) O(Z), s = s.length(), t = t.length(), Z = 26
class Solution76 {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> cnt;
        for(char ch : t)
            ++cnt[ch];

        int l = 0, r = 0, check = 0, start = 0, minLen = INT_MAX;
        while(r < s.length()){
            if(cnt.count(s[r]) && --cnt[s[r]] == 0)//先用count確定是否為要找的字母
                ++check;

            while(check == cnt.size()){
                int len = r - l + 1;
                if(len < minLen){
                    start = l;//記錄位子就好, 直接string... 會Memory Limit Exceeded
                    minLen = len;
                }

                if(cnt.count(s[l]) && cnt[s[l]]++ == 0)
                    --check;

                ++l;
            }
            ++r;
        }
        return minLen == INT_MAX ? "" : s.substr(start, minLen);
    }
};