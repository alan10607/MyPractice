package leetCode.java;

import java.util.*;

//encode(): O(n) O(n), decode(): O(n) O(1)
class Solution271 {//lintcode659
    // Length + Delimiter: "4$lint4$code4$love3$you"
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length())
                .append("$")
                .append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int l = 0;
        while (l < str.length()) {
            int r = l;
            while (str.charAt(r) != '$') {
                r++;
            }

            // 長度會在 [l,r) 之間
            int len = Integer.parseInt(str.substring(l, r));
            res.add(str.substring(r + 1, r + 1 + len));
            l = r + 1 + len; 
        }

        return res;
    }
}