package leetCode.java;

//Two Pointers O(m + n) O(1), m = s.length(), n = t.length()
class Solution844 {
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1, iSkip = 0, jSkip = 0;
        while(i >= 0 || j >= 0){
            while(i >= 0 && (s.charAt(i) == '#' || iSkip > 0)){
                //Java沒有這種寫法: s.charAt(i) == '#' ? iSkip++ : iSkip--;
                iSkip += s.charAt(i) == '#' ? 1 : -1;
                i--;
            }
            while(j >= 0 && (t.charAt(j) == '#' || jSkip > 0)){
                jSkip += t.charAt(j) == '#' ? 1 : -1;
                j--;
            }
            if(i < 0 || j < 0) return i == j;
            if(s.charAt(i--) != t.charAt(j--)) return false;
        }
        return i == j;
    }
}