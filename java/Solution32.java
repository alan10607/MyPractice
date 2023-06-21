package leetCode.java;

import java.util.*;

//Stack O(n) O(n)
class Solution32 {
    public int longestValidParentheses(String s) {
        Deque<Integer> dq = new LinkedList<>();
        dq.push(-1);
        int res = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                dq.push(i);
            }else{//s.charAt(i) == ')'
                dq.poll();
                if(dq.isEmpty()){
                    dq.push(i);
                }else{
                    res = Math.max(res, i - dq.peek());
                }
            }
        }
        return res;
    }
}