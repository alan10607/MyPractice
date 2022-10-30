package leetCode.test2nd;

import java.util.*;

public class Blind75_String {

    //Slide Window
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

    //Stack
    class Solution20 {
        public boolean isValid(String s) {
            Deque<Character> deque = new LinkedList<>();
            Map<Character, Character> bar = new HashMap<>();
            bar.put(')', '(');
            bar.put('}', '{');
            bar.put(']', '[');

            for(int i=0; i<s.length(); i++){
                char ch = s.charAt(i);
                if(bar.containsKey(ch) && bar.get(ch) == deque.peek()){
                    deque.poll();
                }else{
                    deque.push(ch);//從頭加入
                }
            }
            return deque.isEmpty();
        }
    }

    //*LR Pointer
    class Solution125 {
        public boolean isPalindrome(String s) {
            //熟悉以下方法
            //Character.toLowerCase()
            //Character.isLetterOrDigit()
            int l = 0;
            int r = s.length() - 1;
            while(l < r){
                while(l < r && !isASCII(s.charAt(l))){
                    l++;
                }
                while(l < r && !isASCII(s.charAt(r))){
                    r--;
                }
                //記得要轉為小寫再比較
                if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
                    return false;

                l++;
                r--;
            }
            return true;
        }

        public boolean isASCII(char ch){
            return ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z') || ('0' <= ch && ch <= '9');
        }
    }

    //Simulation
    class Solution5 {
        public String longestPalindrome(String s) {
            int max = 0;
            int res[] = new int[2];
            for(int i=0; i<s.length(); i++){
                int[] odd = expend(s, i, i);
                int[] even = expend(s, i, i + 1);
                if(odd[1] - odd[0] > res[1] - res[0]){
                    res[0] = odd[0];
                    res[1] = odd[1];
                }
                if(even[1] - even[0] > res[1] - res[0]){
                    res[0] = even[0];
                    res[1] = even[1];
                }
            }
            return s.substring(res[0], res[1] + 1);
        }

        public int[] expend(String s, int l, int r){
            while(0 <= l && r < s.length() && s.charAt(l) == s.charAt(r)){
                l--;
                r++;
            }
            return new int[]{l + 1, r - 1};//修正
        }
    }

    //Simulation
    class Solution647 {
        public int countSubstrings(String s) {
            int res = 0;
            for(int i=0; i<s.length(); i++){
                res += expendCount(s, i, i);
                res += expendCount(s, i, i + 1);
            }
            return res;
        }

        public int expendCount(String s, int l , int r){
            int count = 0;

            while(0 <= l && r < s.length() && s.charAt(l) == s.charAt(r)){
                count++;
                l--;
                r++;
            }
            return count;
        }
    }

    //Simulation
    public class Solution271 {
        public String encode(List<String> strs) {
            StringBuffer sb = new StringBuffer();
            for(int i=0; i<strs.size(); i++){
                sb.append(strs.get(i).length())
                  .append("$")
                  .append(strs.get(i));
            }
            return sb.toString();
        }

        public List<String> decode(String str) {
            List<String> res = new ArrayList<>();
            int start = 0;
            for(int i=0; i<str.length(); i++){
                if(str.charAt(i) == '$'){
                    int len = Integer.parseInt(str.substring(start, i));
                    res.add(str.substring(i + 1, i + 1 + len));
                    start = i + 1 + len;
                    i = start;
                }
            }
            return res;
        }
    }

}