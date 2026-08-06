package leetCode.java;

import java.util.*;

//Backtracking O(n * n!) O(n), 最差的情況下每個字母可以都是回文, 即需要n!次遞迴
class Solution131 {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtracking(0, new ArrayList<>(), s);
        return res;
    }

    public void backtracking(int start, List<String> strs, String s) {
        if (start == s.length()) {
            res.add(new ArrayList<>(strs));
            return;
        }

        for (int len = 1; start + len <= s.length(); ++len) {
            String str = s.substring(start, start + len);
            if (isPalin(str)) {
                strs.add(str);
                backtracking(start + len, strs, s);
                strs.remove(strs.size() - 1);
            }
        }
    }

    private boolean isPalin(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            ++l;
            --r;
        }
        return true;
    }
}
/* s = "aab"
				[]
		a		aa		aab
     a    ab    b
	 b
*/