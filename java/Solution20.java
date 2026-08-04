package leetCode.java;

import java.util.*;

//Stack O(n) O(n + Z), n = s.length(), Z為括號種類字符集
class Solution20 {
    public boolean isValid(String s) {
        Map<Character, Character> brackets = Map.of(
            ')', '(',
            '}', '{',
            ']', '['
        );
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (brackets.containsKey(ch)) { // 後括號, 嘗試配對
                if (stack.isEmpty() || stack.peek() != brackets.get(ch)) {
                    return false; // 配對失敗, ex: {(})
                }
                stack.pop();
            } else { // 前括號, 累積
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}