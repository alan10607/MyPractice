package leetCode.java;

import java.util.*;

//O(n) O(Z), Z=26
class Solution409 {
    public int longestPalindrome(String s) {
        Set<Character> memo = new HashSet<>();
        int res = 0;
        for(char ch : s.toCharArray()){
            if(memo.contains(ch)){
                memo.remove(ch);
                res += 2;
            }else{
                memo.add(ch);
            }
        }
        return res + (memo.isEmpty() ? 0 : 1);
    }
}