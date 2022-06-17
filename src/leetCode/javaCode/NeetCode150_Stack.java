package leetCode.javaCode;

import java.util.*;

/**
 *  Stack
 */
public class NeetCode150_Stack {

    //Time Complexity: 初始化與所有方法: O(1), Space Complexity: O(n), n為stack大小
    //Stack
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
    //Stack
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

    //Time Complexity: O(n), Space Complexity: O(n), 空間使用在stack上
    //Stack
    class Solution739 {
        public int[] dailyTemperatures(int[] temperatures) {
            //數列左右比較, 考慮使用stack
            Deque<int[]> deque = new LinkedList<>();//[溫度, 日期]
            int[] res = new int[temperatures.length];

            for(int i=0; i<temperatures.length; i++){
                //跟239一樣, 保留最大的
                while(!deque.isEmpty() && deque.peek()[0] < temperatures[i]){
                    int[] last = deque.poll();
                    res[last[1]] = i - last[1];//現在日期 - 之前日期
                }

                deque.push(new int[]{temperatures[i], i});
            }
            return res;
        }
    }

    //Time Complexity: O(n logn), Space Complexity: O(n), 時間複雜度即排序所需
    //Stack
    class Solution853 {
        public int carFleet(int target, int[] position, int[] speed) {
            //先計算每個車到終點的所需時間, 若前車需要更久時間, 則後車也超過這個時間到達
            int[][] car = new int[position.length][2];//[位置, 速度]
            for(int i=0; i<position.length; i++){
                car[i][0] = position[i];
                car[i][1] = speed[i];
            }

            Arrays.sort(car, (c1, c2) -> c1[0] - c2[0]);//依照位置排列;

            Deque<Double> times = new LinkedList<>();
            for(int i=0; i<car.length; i++){
                double time = (double) (target - car[i][0]) / car[i][1];
                while(!times.isEmpty() && times.peek() <= time){//等於的話, 也要拿掉避免重複
                    times.poll();
                }
                times.push(time);
            }
            return times.size();
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n)
    //Stack
    class Solution84 {
        public int largestRectangleArea(int[] heights) {
            //保留最大的高度
            Deque<int[]> stack = new LinkedList<>();//[位置, 高度]
            int res = 0;
            for(int i=0; i<heights.length; i++){
                int posi = i;
                //若高度比當前的大就pop, 並計算大小
                while(!stack.isEmpty() && stack.peek()[1] > heights[i]){
                    int[] data = stack.poll();
                    res = Math.max(res, (i - data[0]) * data[1]);//底 * 高
                    posi = data[0];//將此次位置後移
                }
                stack.push(new int[]{posi, heights[i]});
            }

            while(!stack.isEmpty()){
                int[] data = stack.poll();
                res = Math.max(res, (heights.length - data[0]) * data[1]);//底 * 高
            }

            return res;
        }
        /*
        [1, 5, 6, 2]
        stack = [[0, 1]]
        stack = [[0, 1], [1, 5]]
        stack = [[0, 1], [1, 5]]
        stack = [[0, 1], [1, 5], [2, 6]]
        stack = [[0, 1], [1, 5], [2, 6], [3, 2]] => [[0, 1], [1, 2]], area = [(3 - 1) * 5, (2 - 1) * 6]
        stack = [[0, 1], [1, 2]], 結算, area = [(4 - 1) * 2, (4 - 0) * 1], maxArea = 10
        */
    }

}