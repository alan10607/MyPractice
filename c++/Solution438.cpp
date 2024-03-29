//Slide Window O(m + n) O(Z), m = s.length(), n = p.length(), Z = 26
class Solution438 {
public:
    vector<int> findAnagrams(string s, string p) {
        unordered_map<char, int> cnt;//<p的字母, 出現次數>
        for(char ch : p)
            ++cnt[ch];

        int check = 0;
        vector<int> res;
        for(int r=0; r<s.length(); ++r){
            if(cnt.count(s[r]) && --cnt[s[r]] == 0)
                ++check;//右指標新增

            int l = r - p.length();
            if(l >= 0 && cnt.count(s[l]) && cnt[s[l]]++ == 0)
                --check;//左指標刪去

            if(check == cnt.size())
                res.push_back(l + 1);//l是要刪去的左界, 剛好是l的下一個
        }

        return res;
    }
};
/*
c   b   a   e   b   a   b   a   c   d
                        ---------
                    ^l          ^r
*/