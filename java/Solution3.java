package leetCode.java;

import java.util.*;

//Slide Window O(n) O(Z), Z = 26
class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> memo = new HashSet<>();
        int res = 0;
        int l = 0;
        int r = 0;
        while(r < s.length()){
            //如果下一個r已存在, 移動l直到沒有重複
            if(memo.contains(s.charAt(r))){
                memo.remove(s.charAt(l));
                l++;
            }else{
                memo.add(s.charAt(r));
                res = Math.max(res, r - l + 1);
                r++;
            }
        }
        return res;
    }
}