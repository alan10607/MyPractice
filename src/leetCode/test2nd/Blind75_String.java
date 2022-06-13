package leetCode.test2nd;

import java.util.*;

public class Blind75_String {

    //Slide window
    class Solution3 {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> memo = new HashSet<>();
            int l = 0;
            int r = 0;
            int max = 0;
            while(r < s.length()){
                char ch = s.charAt(r);
                if(!memo.contains(ch)){
                    memo.add(ch);
                    max = Math.max(max, memo.size());
                    r++;
                }else{
                    memo.remove(s.charAt(l));
                    l++;
                }
            }
            return max;
        }
    }

    //*Slide window
    class Solution424 {
        public int characterReplacement(String s, int k) {
            int l = 0;
            int r = 0;
            int[] count = new int[26];
            int maxCount = 0;
            int res = 0;
            while(r < s.length()){
                char rCh = s.charAt(r);
                count[rCh - 'A']++;
                maxCount = Math.max(maxCount, count[rCh - 'A']);//只記錄出現最多的那個

                //可以用if因為l++後必定會 == k
                if(r - l + 1 - maxCount > k){
                    count[s.charAt(l) - 'A']--;
                    l++;
                }

                res = r - l + 1;
                r++;
            }
            return res;
        }
    }

    //*Slide window
    class Solution76 {
        public String minWindow(String s, String t) {
            if(s.length() < t.length()) return "";
            Map<Character, Integer> need = new HashMap<>();
            for(char ch : t.toCharArray())
                need.put(ch, need.getOrDefault(ch, 0) + 1);

            int l = 0;
            int r = 0;
            int okCount = 0;
            int minLen = Integer.MAX_VALUE;
            int start = 0;
            int end = 0;
            while(r < s.length()){
                char rCh = s.charAt(r);
                if(need.containsKey(rCh)){
                    need.put(rCh, need.get(rCh) - 1);
                    if(need.get(rCh).equals(0))
                        okCount++;
                }

                while(okCount == need.size()){
                    //符合條件, 先更新數據再慢慢求最好
                    if(r - l + 1 < minLen){
                        minLen = r - l + 1;
                        start = l;
                        end = r;
                    }

                    char lCh = s.charAt(l);
                    if(need.containsKey(lCh)){
                        need.put(lCh, need.get(lCh) + 1);
                        if(need.get(lCh) > 0)
                            okCount--;
                    }
                    l++;
                }
                r++;
            }

            return minLen > s.length() ? "" : s.substring(start, end + 1);
        }
    }

    //Array
    class Solution242 {
        public boolean isAnagram(String s, String t) {
            if(s.length() != t.length()) return false;

            int[] counts = new int[26];
            for(char ch : s.toCharArray())
                counts[ch - 'a']++;

            for(char ch : t.toCharArray()){
                counts[ch - 'a']--;
                if(counts[ch - 'a'] < 0)
                    return false;
            }

            return true;
        }
    }

    //HaspMap
    class Solution49 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> memo = new HashMap<>();

            for(String str : strs){
                char[] hash = new char[26];
                for(char ch : str.toCharArray())
                    hash[ch - 'a']++;

                String word = new String(hash);
                if(!memo.containsKey(word)) memo.put(word, new ArrayList());
                memo.get(word).add(str);
            }

            List<List<String>> res = new ArrayList(memo.values());
            return res;
        }
    }

}