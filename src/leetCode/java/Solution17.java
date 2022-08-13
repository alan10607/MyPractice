package leetCode.java;

import java.util.*;

//Backtracking O(3^m 4^n) O(m + n), m, n代表digits中3或4個字母的數字個數
class Solution17 {
    public String[] letters = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.isEmpty()) return res;

        backtracking(0, new StringBuffer(), digits, res);
        return res;
    }

    public void backtracking(int i, StringBuffer sb, String digits, List<String> res){
        if(sb.length() == digits.length()){
            res.add(sb.toString());
            return;
        }

        int num = digits.charAt(i) - '0';
        String letter = letters[num];
        for(char ch : letter.toCharArray()){
            sb.append(ch);
            backtracking(i + 1, sb, digits, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}