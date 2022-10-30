package leetCode.java;

import java.util.*;

//Slide Window O(n) O(Z), n = s2.length(), Z最多為26
class Solution567 {
    public boolean checkInclusion(String s1, String s2) {
        //permutation表示要黏在一起的交換字
        Map<Character, Integer> counts = new HashMap<>();//<字母, 出現次數>
        for(char ch : s1.toCharArray())
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);

        int check = 0;
        int gap = s1.length();//window的區間
        for(int i=0; i<s2.length(); i++){
            char rCh = s2.charAt(i);
            if(counts.containsKey(rCh)){//加入右側
                counts.put(rCh, counts.get(rCh) - 1);
                if(counts.get(rCh) == 0) check++;
            }

            if(i - gap >= 0){//超出視窗, 刪除左側
                char lCh = s2.charAt(i - gap);
                if(counts.containsKey(lCh)){
                    if(counts.get(lCh) == 0) check--;
                    counts.put(lCh, counts.get(lCh) + 1);
                }
            }

            if(check == counts.size()) return true;
        }

        return false;
    }
}