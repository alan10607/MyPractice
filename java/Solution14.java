package leetCode.java;

//O(mn) O(1), m = strs.length, n = strs[0].length()
class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        int len = 0;
        for (int i = 0; i < strs[0].length(); ++i) {
            char ch = strs[0].charAt(i);
            for (int j = 1; j < strs.length; ++j) {
                if (i >= strs[j].length() || ch != strs[j].charAt(i)) {
                    return strs[0].substring(0, len);
                }
            }
            ++len;
        }
        return strs[0];
    }
}