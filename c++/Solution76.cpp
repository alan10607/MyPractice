// Slide Window O((s + t) * Z) O(Z), s = s.length(), t = t.length(), Z = 26
class Solution76 {
public:
    string minWindow(string s, string t) {
        unordered_map<char, int> cnt;
        for (char ch : t)
            ++cnt[ch];

        int l = 0, r = 0, check = 0, start = 0, min_len = INT_MAX;
        while (r < s.length()) {
            if (cnt.count(s[r]) &&
                --cnt[s[r]] == 0) // 先用count確定是否為要找的字母
                ++check;

            while (check == cnt.size()) {
                int len = r - l + 1;
                if (len < min_len) {
                    start = l; // 記錄位子就好, 直接string... 會Memory Limit Exceeded
                    min_len = len;
                }

                if (cnt.count(s[l]) && cnt[s[l]]++ == 0)
                    --check;

                ++l;
            }
            ++r;
        }
        return min_len == INT_MAX ? "" : s.substr(start, min_len);
    }
};



//Slide Window O((s + t) * Z) O(Z), s = s.length(), t = t.length(), Z = 26
class Solution76_2 {
public:
    string minWindow(string s, string t) {
        vector<int> cnt(128, 0);//用array也可以做這題, 最初的ascii定義了128個字元
        for(char ch : t) {
            ++cnt[ch];
        }

        int l = 0, r = 0;
        int check = 0, min_len = INT_MAX, start = 0;
        while(r < s.length()){
            if(--cnt[s[r]] >= 0) {
                ++check;
            }

            while(check == t.length()){
                int len = r - l + 1;
                if(min_len > len) {
                    start = l;
                    min_len = len;
                }

                if(++cnt[s[l]] > 0){
                    --check;
                }
                ++l;
            }
            ++r;
        }

        return min_len == INT_MAX ? "" : s.substr(start, min_len);
    }
};