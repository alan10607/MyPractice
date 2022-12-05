package leetCode.java;

//O(mn) O(1), m = strs.length, n = strs[0].length()
class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        for(int i=0; i<strs[0].length(); i++){
            char ch = strs[0].charAt(i);
            for(String str : strs){
                if(i >= str.length() || ch != str.charAt(i))
                    return str.substring(0, i);
            }
        }
        return strs[0];
    }
}