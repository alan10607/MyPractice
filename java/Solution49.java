package leetCode.java;

import java.util.*;

//Stack O(nk) O(nZ), n = strs.length, k為最大的str長度, Z = 26
class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>(); // <hash, <str1, str2,...>>

        for (String str : strs) {
            char[] hashArray = new char[26];
            for (char ch : str.toCharArray()) {
                hashArray[ch - 'a']++;
            }
            String hash = new String(hashArray);
            groups.putIfAbsent(hash, new ArrayList<>());
            groups.get(hash).add(str);
        }

        return new ArrayList<>(groups.values());
    }
}