package leetCode.java;

import java.util.*;

//Greedy Slide Window O(n) O(Z), Z = 26
class Solution763 {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();//<字母, 最後出現的位置>
        for(int i=0; i<s.length(); i++)
            lastIndex.put(s.charAt(i), i);

        List<Integer> res = new ArrayList<>();
        int l = 0;
        int r = 0;
        for(int i=0; i<s.length(); i++){
            r = Math.max(r, lastIndex.get(s.charAt(i)));
            if(i == r){//走到斷點
                res.add(r - l + 1);
                l = r + 1;
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