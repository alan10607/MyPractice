package leetCode.java;

//Expand O(n^2) O(1)
class Solution647 {
    public int countSubstrings(String s) {
        int res = 0;
        for(int i=0; i<s.length(); i++){
            int countOdd = expand(i, i, s);
            int countEven = expand(i, i + 1, s);
            res += countOdd + countEven;
        }
        return res;
    }

    public int expand(int l, int r, String s){
        int count = 0;
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
            count++;
        }
        return count;
    }
}