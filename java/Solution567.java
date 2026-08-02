package leetCode.java;

import java.util.*;

//Slide Window O(m + n) O(Z), m = s1.length(), n = s2.length(), Z=26
class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        //permutation排列, 表示要黏在一起的交換字
        int[] cnt = new int[26]; // 這題用map or array都行, 但array更快
        for (char ch : s1.toCharArray()) {
            ++cnt[ch - 'a'];
        }

        int check = s1.length(); // 代表還要滿足多少s1
        for (int r = 0; r < s2.length(); ++r) {
            // 若cnt[ch]>0, 代表ch是s1裡面需要的, 若cnt[ch] <= 0, 代表是不需要的, 忽略即可
            if (cnt[s2.charAt(r) - 'a']-- > 0) {
                --check;
            }

            int l = r - s1.length();
            if (l >= 0) {
                if (++cnt[s2.charAt(l) - 'a'] > 0) {
                    ++check;
                }
            }

            if (check == 0) {
                return true;
            }
        }
        return false;
    }
}