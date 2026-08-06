package leetCode.java;

import java.util.*;

//Backtracking O(3^m 4^n) O(m + n), m, n代表digits中3或4個字母的數字個數
class Solution17 {
    String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) {
            return res;
        }

        backtracking(0, "", digits);
        return res;
    }

    public void backtracking(int i, String str, String digits) {
        if (i == digits.length()) {
            res.add(str);
            return;
        }

        int num = digits.charAt(i) - '0';
        for (char ch : letters[num].toCharArray()) {
            backtracking(i + 1, str + ch, digits);
        }
    }
}
/*
digits = "23"

                        []
        a               b               c
    ad  ae  af      bd  be  bf      cd  ce  cf

*/