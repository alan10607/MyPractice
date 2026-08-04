package leetCode.java;

import java.util.*;

//Stack MinStack(), push(), pop(), top(), getMin(): O(1) O(n)
class MinStack {//Solution155
    Deque<Integer> st = new ArrayDeque<>();
    Deque<Integer> minSt = new ArrayDeque<>(); // 每個stack element都保存當它存在時的最小值

    public MinStack() {
    }
    
    public void push(int value) {
        st.push(value);
        int minValue = minSt.isEmpty() ? value : Math.min(minSt.peek(), value);
        minSt.push(minValue);
    }
    
    public void pop() {
        st.pop();
        minSt.pop();
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return minSt.peek();
    }
}
/*
本題是經典的 "額外維護狀態"的技巧，在stack每個元素都保存當它存在時的最小值
stack	minStack
1		-3
-3		-3
0		-2
-2		-2
*/