package leetCode.java;

import java.util.*;

//Stack O(n) O(num), num為tokens中為數字者
class Solution150 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> st = new ArrayDeque<>();
        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                int b = st.pop();
                int a = st.pop();
                if ("+".equals(token)) {
                    st.push(a + b);
                } else if ("-".equals(token)) {
                    st.push(a - b);
                } else if ("*".equals(token)) {
                    st.push(a * b);
                } else if ("/".equals(token)) {
                    st.push(a / b);
                }
            } else {
                st.push(Integer.parseInt(token));
            }
        }
        return st.peek();
    }
}
/* tokens = ["4","13","5","/","-"]
=> 4 - (13 / 5)
*/