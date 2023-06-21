package leetCode.java;

import java.util.*;

//Stack pop(), empty(), pop(), peek(): O(1) O(n), 除了moveToOld()時需要O(n)時間
class MyQueue {//Solution232
    Stack<Integer> newSt;
    Stack<Integer> oldSt;

    public MyQueue() {
        newSt = new Stack<>();
        oldSt = new Stack<>();
    }

    public void push(int x) {
        newSt.push(x);
    }

    public int pop() {
        moveToOld();
        return oldSt.pop();
    }

    public int peek() {
        moveToOld();
        return oldSt.peek();
    }

    public boolean empty() {
        return newSt.isEmpty() && oldSt.isEmpty();
    }

    private void moveToOld(){
        if(!oldSt.isEmpty()) return;

        while(!newSt.isEmpty())
            oldSt.push(newSt.pop());//反向入列
    }
}