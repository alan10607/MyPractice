package leetCode.java;

//Backtracking DP O(n) O(n), n = p.length
class Solution10 {
    public boolean isMatch(String s, String p) {
        return isMatch(0, 0, s, p);
    }

    public boolean isMatch(int i, int j, String s, String p) {
        int m = s.length(), n = p.length();
        if (i == m && j == n) {
            return true;
        }
        if (i == m) { // p嘗試配對a*, 移除多餘pattern
            return (j + 1 < n) && (p.charAt(j + 1) == '*') && isMatch(i, j + 2, s, p);
        }
        if (j == n) { // pattern已用完但還有沒配對上的
            return false;
        }

        boolean firstMatch = s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
        if (j + 1 < n && p.charAt(j + 1) == '*') { // a*或.*的情況
            return (firstMatch && isMatch(i + 1, j, s, p)) || // 使用
                     isMatch(i, j + 2, s, p); // 不使用

        } else { // 一般配對
            return firstMatch && isMatch(i + 1, j + 1, s, p);
        }
    }
}
/* 
_代表空白, s跟p剩下甚麼會是甚麼結果:

s   p   res
_   _   T
_   a*  T
_   .*  T
_   a   F
b   .   T
b   _   F
b   a   F

*/