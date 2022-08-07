package leetCode.java;

import java.util.*;

//Stack O(n) O(num), num為tokens中為數字者
class Solution150 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> deque = new LinkedList<>();
        for(String token : tokens){
            if("+".equals(token)){
                deque.push(deque.poll() + deque.poll());
            }else if("-".equals(token)){
                int a = deque.poll();
                int b = deque.poll();
                deque.push(b - a);
            }else if("*".equals(token)){
                deque.push(deque.poll() * deque.poll());
            }else if("/".equals(token)){
                int a = deque.poll();
                int b = deque.poll();
                deque.push(b / a);
            }else{
                deque.push(Integer.parseInt(token));
            }
        }
        return deque.peek();
    }
}
/* tokens = ["4","13","5","/","-"]
=> 4 - (13 / 5)
*/