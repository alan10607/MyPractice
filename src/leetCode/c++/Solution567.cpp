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