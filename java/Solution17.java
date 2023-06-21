package leetCode.java;

import java.util.*;

//Backtracking O(3^m 4^n) O(m + n), m, n代表digits中3或4個字母的數字個數
class Solution17 {
    public List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return res;

        String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtracking(0, "", digits, letters);
        return res;
    }

    public void backtracking(int i, String str, String digits, String[] letters){
        if(str.length() == digits.length()){
            res.add(str);
            return;
        }

        String letter = letters[digits.charAt(i) - '0'];
        for(char ch : letter.toCharArray())
            backtracking(i + 1, str + ch, digits, letters);
    }
}