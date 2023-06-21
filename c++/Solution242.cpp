//O(n) O(Z), Z = 26
class Solution242 {
public:
    bool isAnagram(string s, string t) {
        //Anagram=字謎, 易位構詞
        if(s.length() != t.length()) return false;
        int count[26] = {0};
        for(int i=0; i<s.length(); ++i)
            ++count[s[i] - 'a'];

        for(int i=0; i<t.length(); ++i){
            if(--count[t[i] - 'a'] < 0)
                return false;
        }
        return true;
    }
};