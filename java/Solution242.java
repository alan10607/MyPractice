package leetCode.java;

//O(n) O(Z), Z = 26
class Solution242 {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] count = new int[26];//s and t consist of lowercase English letters.
        for(char ch : s.toCharArray())
            count[ch - 'a']++;

        for(char ch : t.toCharArray()){
            if(--count[ch - 'a'] < 0)
                return false;
        }
        return true;
    }
}