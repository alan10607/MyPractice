package leetCode.java;

import java.util.*;

//Backtracking O(n * 2^k) O(n), n = s.length(), k為s中為字母的長度
class Solution784 {
    List<String> res = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        backtracking(0, s.toCharArray());
        return res;
    }

    public void backtracking(int i, char[] ch){
        if(i == ch.length){
            res.add(new String(ch));
            return;
        }
        if(ch[i] >= '0' && ch[i] <= '9'){
            backtracking(i + 1, ch);
            return;
        }

        backtracking(i + 1, ch);

        ch[i] ^= 32;
        backtracking(i + 1, ch);
    }
}