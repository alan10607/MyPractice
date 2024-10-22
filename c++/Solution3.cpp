//Slide Window O(n) O(Z), Z = 26
class Solution3 {
public:
    int lengthOfLongestSubstring(string s) {
        unordered_set<char> memo;
        int l = 0, r = 0, res = 0;
        while(r < s.length()){
            if(memo.count(s[r])){
                memo.erase(s[l]);
                ++l;
            }else{
                memo.insert(s[r]);
                res = max(res, r - l + 1);
                ++r;
            }
        }
        return res;
    }
};


//Slide Window O(n) O(Z), Z = 128
class Solution3_2 {
public:
    int lengthOfLongestSubstring(string s) {
        vector<int> cnt(128);
        int l = 0, r = 0;
        int res = 0;
        while (r < s.length()) {
            char ch = s[r];
            ++cnt[ch];
            ++r;

            while (cnt[ch] > 1) {
                --cnt[s[l]];
                ++l;
            }

            res = max(res, r - l);
        }
        return res;
    }
};