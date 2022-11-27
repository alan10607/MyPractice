package leetCode.java;

import java.util.*;

//Slide Window O(m + n) O(Z), m = s.length(), n = p.length(), Z = 26
class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> cnt = new HashMap<>();//<p的字母, 出現次數>
        for(char ch : p.toCharArray())
            cnt.put(ch, cnt.getOrDefault(ch, 0) + 1);

        int check = 0;
        List<Integer> res = new ArrayList<>();
        for(int r=0; r<s.length(); r++){
            char rCh = s.charAt(r);
            if(cnt.containsKey(rCh)){
                cnt.put(rCh, cnt.get(rCh) - 1);
                if(cnt.get(rCh) == 0) check++;
            }

            int l = r - p.length();
            if(l >= 0){
                char lCh = s.charAt(l);
                if(cnt.containsKey(lCh)){
                    if(cnt.get(lCh) == 0) check--;
                    cnt.put(lCh, cnt.get(lCh) + 1);
                }
            }

            if(check == cnt.size())
                res.add(l + 1);
        }

        return res;
    }
}