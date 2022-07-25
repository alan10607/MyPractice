package leetCode.java;

import java.util.*;

//Slide window O((s + t) * Z) O(Z), s = s.length(), t = t.length(), Z = 26
class Solution76 {
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";

        //1 建立counts HashMap
        Map<Character, Integer> counts = new HashMap<>();
        for(char ch : t.toCharArray())
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);

        //2 slide window
        int match = 0;
        int l = 0;
        int r = 0;
        String res = "";//如果都沒符合就回default空白
        while(r < s.length()){
            char rCh = s.charAt(r);
            if(counts.containsKey(rCh)){
                counts.put(rCh, counts.get(rCh) - 1);
                if(counts.get(rCh) == 0)
                    match++;
            }

            while(match == counts.size()){
                //符合條件後, 開始更新最短可能
                if("".equals(res) || (r - l + 1) < res.length())
                    res = s.substring(l, r + 1);

                char lCh = s.charAt(l);
                if(counts.containsKey(lCh)){
                    if(counts.get(lCh) == 0)
                        match--;
                    counts.put(lCh, counts.get(lCh) + 1);
                }
                l++;
            }
            r++;
        }
        return res;
    }
}
/* s = "ADOBECODEBANC", t = "ABC"
ADOBECODEBANC r++
^l&r
ADOBECODEBANC 符合條件, l++, 最後得到ADOBEC
^    ^
ADOBECODEBANC 不符合條件, r++
 ^   ^
ADOBECODEBANC 符合條件, l++, 最後得到CODEBA
 ^        ^
ADOBECODEBANC 不符合條件, r++
      ^   ^
ADOBECODEBANC 符合條件, l++, 最後得到BANC
      ^     ^
ADOBECODEBANC 不符合條件, r++, break
          ^ ^
*/