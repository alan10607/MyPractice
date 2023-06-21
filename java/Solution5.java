package leetCode.java;

//Expand O(n^2) O(1)
class Solution5 {
    public String longestPalindrome(String s) {
        int[] res = new int[2];
        for(int i=0; i<s.length(); i++){
            int[] odd = expand(i, i, s);
            int[] even = expand(i, i + 1, s);
            int max = res[1] - res[0];
            if((odd[1] - odd[0]) > max) res = odd;
            if((even[1] - even[0]) > max) res = even;
        }
        return s.substring(res[0], res[1] + 1);
    }

    public int[] expand(int l, int r, String s){
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return new int[]{l + 1, r - 1};//修正到可展開範圍
    }
}