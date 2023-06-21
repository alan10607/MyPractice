package leetCode.java;

//LR Pointer O(n) O(1)
class Solution125 {
    public boolean isPalindrome(String s) {
        //Character.toLowerCase(), Character.isLetterOrDigit()
        int l = 0;
        int r = s.length() - 1;
        while(l < r){
            while(l < r && !Character.isLetterOrDigit(s.charAt(l)))
                l++;

            while(l < r && !Character.isLetterOrDigit(s.charAt(r)))
                r--;

            if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                return false;

            l++;
            r--;
        }
        return true;
    }
}