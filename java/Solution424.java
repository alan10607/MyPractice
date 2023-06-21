package leetCode.java;

//Slide Window O(n) O(Z), Z = 26
class Solution424 {
    public int characterReplacement(String s, int k) {
        int[] counts = new int[26];
        int maxCount = 0;//用來記錄最出現最多的字母的個數
        int res = 0;
        int l = 0;
        int r = 0;
        while(r < s.length()){
            char rCh = s.charAt(r);
            counts[rCh - 'A']++;
            maxCount = Math.max(maxCount, counts[rCh - 'A']);

            //超過替換個數, 可以用if因為l++後必為false
            if(r - l + 1 > maxCount + k){
                char lCh = s.charAt(l);
                counts[lCh - 'A']--;
                l++;
            }

            res = Math.max(res, r - l + 1);
            r++;
        }
        return res;
    }
}