//Backtracking O(n) O(n), n = p.length
class Solution10 {
public:
    bool isMatch(string s, string p) {
        return isMatch(0, 0, s, p);
    }

    bool isMatch(int i, int j, string& s, string& p){//記得用&否則Memory Limit Exceeded
        if(i == s.length() && j == p.length()) return true;
        if(i == s.length()) return j + 1 < p.length() && p[j + 1] == '*' && isMatch(i, j + 2, s, p);//濾掉p=".*"的情況
        if(j == p.length()) return false;

        if(j + 1 < p.length() && p[j + 1] == '*'){
            return ((s[i] == p[j] || p[j] == '.') && isMatch(i + 1, j, s, p)) 
                    || isMatch(i, j + 2, s, p); //跳過 a*這種preceding element
        }else{
            return (s[i] == p[j] || p[j] == '.') && isMatch(i + 1, j + 1, s, p);
        }
    }
};
/* _代表空白, s跟p剩下甚麼會是甚麼結果:
s   p   res
_   _   T
_   a*  T
_   .*  T
_   a   F
b   _   F
b   a   F

*/