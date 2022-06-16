package leetCode.javaCode;

import java.util.*;

/**
 *  Stack
 */
public class NeetCode150_Stack {

    //Time Complexity: 初始話與所有方法: O(1), Space Complexity: O(n), n為stack大小
    //Deque
    class Solution155 {
        class MinStack {
            public Deque<Integer> stack;//Deque才有push()
            public Deque<Integer> minStack;//頭為最小

            public MinStack() {
                stack = new LinkedList<>();
                minStack = new LinkedList<>();
            }

            public void push(int val) {
                //只從頭出去, 不用考慮到從中間remove
                stack.push(val);
                minStack.push(minStack.isEmpty() ? val : Math.min(minStack.peek(), val));
            }

            public void pop() {
                stack.pop();
                minStack.pop();
            }

            public int top() {
                return stack.peek();
            }

            public int getMin() {
                return minStack.peek();
            }
            /* ex: [2, 1, 3, 4]
            stack = [4, 3, 1, 2]
            minStack = [1, 1, 1, 2]
            */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n), 空間使用在stack上
    //Deque
    class Solution150 {
        public int evalRPN(String[] tokens) {
            Deque<Integer> stack = new LinkedList<>();
            for(int i=0; i<tokens.length; i++){
                if("+".equals(tokens[i])){
                    stack.push(stack.poll() + stack.poll());
                }else if("-".equals(tokens[i])){
                    int a = stack.poll();//21- => 2-1 => b-a
                    int b = stack.poll();
                    stack.push(b - a);
                }else if("*".equals(tokens[i])){
                    stack.push(stack.poll() * stack.poll());
                }else if("/".equals(tokens[i])){
                    int a = stack.poll();//21/ => 2/1 => b/a
                    int b = stack.poll();
                    stack.push(b / a);
                }else{
                    stack.push(Integer.parseInt(tokens[i]));
                }
            }
            return stack.peek();
        }
    }

    //Time Complexity: O((4^n)/(n^(1/2)))(卡塔蘭數), Space Complexity: O(n)
    //Recursion
    class Solution22 {
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            backtrack(n, n, "", res);
            return res;
        }

        public void backtrack(int open, int close, String str, List<String> res){
            if(open > close || open < 0 || close < 0) return;

            if(open == 0 && close == 0){
                res.add(str);
                return;
            }

            backtrack(open - 1, close, str + "(", res);
            backtrack(open, close - 1, str + ")", res);
            return;
        }

        public List<String> generateParenthesis2(int n) {
            List<String> res = new ArrayList<>();
            backtrack(0, 0, new StringBuffer(), res, n);
            return res;
        }

        public void backtrack(int open, int close, StringBuffer sb, List<String> res, int n){
            if(open == n && close == n){
                res.add(sb.toString());
                return;
            }

            if(open < n){
                sb.append("(");
                backtrack(open + 1, close, sb, res, n);
                sb.deleteCharAt(sb.length() - 1);
                //要記得減回來, 因為sb是物件, 如果是string就不用, 但那樣比較耗資源
            }

            if(close < open){
                sb.append(")");
                backtrack(open, close + 1, sb, res, n);
                sb.deleteCharAt(sb.length() - 1);
            }
            return;
        }
        /**                        (
         ((                              ()
         (((            (()              ()(
         (()(   (())      ()((   ()()
         (())(                ()()(

         ((()))    (()()) (())()      ()(())   ()()()
         */
    }

}