//Slide Window O(m + n) O(Z), m = s1.length(), n = s2.length(), Z=26
class Solution567 {
public:
    bool checkInclusion(string s1, string s2) {
        if(s1.length() > s2.length()) return false;

        unordered_map<char, int> cnt;
        for(char ch : s1)
            ++cnt[ch];

        int check = 0;
        for(int r=0; r<s2.length(); ++r){
            if(cnt.count(s2[r]) && --cnt[s2[r]] == 0) ++check;
            int l = r - s1.length();
            if(l >= 0 && cnt.count(s2[l]) && cnt[s2[l]]++ == 0) --check;

            if(check == cnt.size()) return true;
        }
        return false;
    }
};

//Slide Window O(m + n) O(Z), m = s1.length(), n = s2.length(), Z=128
class Solution567_2 {
public:
    bool checkInclusion(string s1, string s2) {
        vector<int> cnt(128);
        for (char ch : s1) {
            ++cnt[ch];
        }

        int l = 0, r = 0;
        int check = 0;
        while (r < s2.length()) {
            if (cnt[s2[r]]-- > 0) {
                ++check;
            }
            ++r;

            if (r > l + s1.length()) {
                if (++cnt[s2[l]] > 0) {
                    --check;
                }
                ++l;
            }

            if (check == s1.length()) {
                return true;
            }
        }

        return false;
    }
};