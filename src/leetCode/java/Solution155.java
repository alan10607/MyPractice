package leetCode.java;

import java.util.*;

//Stack MinStack(), push(), pop(), top(), getMin(): O(1) O(n)
class MinStack {//Solution155
    Deque<Integer> stack;
    Deque<Integer> minStack;//存入當前最小存入當前最小

    public MinStack() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }

    public void push(int val) {
        stack.push(val);
        int min = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
        minStack.push(min);
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
}
/*
stack	minStack
1		-3
-3		-3
0		-2
-2		-2
*/