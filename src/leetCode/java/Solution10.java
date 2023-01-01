package leetCode.java;

//Backtracking O(n) O(n), n = p.length
class Solution10 {
    public boolean isMatch(String s, String p) {
        return isMatch(0, 0, s, p);
    }

    public boolean isMatch(int i, int j, String s, String p){
        if(j == p.length()) return i == s.length();

        //s為空時交給下方判斷
        if(j + 1 < p.length() && p.charAt(j + 1) == '*'){
            return isMatch(i, j + 2, s, p) //跳過不使用
                    || (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && isMatch(i + 1, j, s, p));
        }else{
            return i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && isMatch(i + 1, j + 1, s, p);
        }

    }
}
/*
離開條件
s   p
_   _   T
ab  _   F
_   ab  F
_   a*  T

*/