package leetCode.java;

import java.util.*;

//Slide Window O((s + t) * Z) O(Z), s = s.length(), t = t.length(), Z = 26
class Solution76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> cnt = new HashMap<>();
        for (char ch : t.toCharArray()) {
            cnt.put(ch, cnt.getOrDefault(ch, 0) + 1);
        }

        int l = 0, r = 0, check = cnt.size(), start = 0, minLen = Integer.MAX_VALUE;
        while (r < s.length()) {
            char rCh = s.charAt(r);
            if (cnt.containsKey(rCh)) {
                cnt.put(rCh, cnt.get(rCh) - 1);
                if (cnt.get(rCh) == 0) {
                    --check;
                }
            }

            while (check == 0) {
                int len = r - l + 1;
                if (len < minLen) {
                    start = l; // 記錄位子就好, 直接string... 有機會Memory Limit Exceeded
                    minLen = len;
                }

                char lCh = s.charAt(l);
                if (cnt.containsKey(lCh)) {
                    if (cnt.get(lCh) == 0) {
                        ++check;
                    }
                    cnt.put(lCh, cnt.get(lCh) + 1);
                }
                ++l;
            }
            ++r;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
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