package leetCode.java;

import java.util.*;

//Stack O(n) O(n + Z), n = s.length(), Z為括號種類字符集
class Solution20 {
    public boolean isValid(String s) {
        Map<Character, Character> bar = new HashMap<>();
        bar.put(')', '(');
        bar.put('}', '{');
        bar.put(']', '[');

        Deque<Character> stack = new LinkedList<>();
        for(char ch : s.toCharArray()){
            if(bar.containsKey(ch)){
                if(!stack.isEmpty() && stack.peek() == bar.get(ch)){
                    stack.poll();//成功配對
                }else{
                    return false;//{(}的情況
                }
            }else{
                stack.push(ch);
            }
        }
        return stack.isEmpty();//查看是否有漏掉的左括號
    }
}