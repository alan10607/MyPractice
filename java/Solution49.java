package leetCode.java;

import java.util.*;

//Stack O(nk) O(nZ), n = strs.length, k為最大的str長度, Z = 26
class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();//<hash, <str1, str2,...>>
        for(String str : strs){
            char[] hash = new char[26];
            for(char ch : str.toCharArray())
                hash[ch - 'a']++;

            String hashStr = new String(hash);
            if(!groups.containsKey(hashStr))
                groups.put(hashStr, new ArrayList<String>());

            groups.get(hashStr).add(str);
        }
        return new ArrayList<List<String>>(groups.values());
    }
}