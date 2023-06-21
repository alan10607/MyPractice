//Two Pointers O(m + n) O(1), m = s.length(), n = t.length()
class Solution844 {
public:
    bool backspaceCompare(string s, string t) {
        //solve it in O(n) time and O(1) space
        int i = s.length() - 1, j = t.length() - 1, iSkip = 0, jSkip = 0;
        while(i >= 0 || j >= 0){
            while(i >= 0 && (s[i] == '#' || iSkip > 0)){
                s[i] == '#' ? ++iSkip : --iSkip;
                --i;
            }
            while(j >= 0 && (t[j] == '#' || jSkip > 0)){
                t[j] == '#' ? ++jSkip : --jSkip;
                --j;
            }
            if(i < 0 || j < 0) return i == j;
            if(s[i--] != t[j--]) return false;//記得要--
        }
        return i == j;//如果同時結束會到這
    }
};