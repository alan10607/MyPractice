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