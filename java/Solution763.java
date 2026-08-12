package leetCode.java;

import java.util.*;

//Greedy Slide Window O(n) O(Z), Z = 26
class Solution763 {
    public List<Integer> partitionLabels(String s) {
        int[] lastIndex = new int[26]; // 該字母最後出現的位置
        for (int i = 0; i < s.length(); ++i) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        int l = 0, r = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            l = Math.max(l, lastIndex[s.charAt(i) - 'a']);
            if (l == i) { // 走到斷點, 完成一個段落
                res.add(l - r + 1);
                r = l + 1;
            }
        }
        return res;
    }
}
/*
a b c b a d e f f g e
--------- - ---------
  -----       --- -
    -

=>[5,1,5]

*/