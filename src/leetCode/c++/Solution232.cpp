//Stack pop(), empty(), pop(), peek(): O(1) O(n), 除了moveToOld()時需要O(n)時間
class MyQueue {//Solution232
private:
    stack<int> newStack;
    stack<int> oldStack;

    void moveToOld(){
        if(!oldStack.empty()) return;//等舊的stack都pop到為空才可加入, 否則順序會亂掉

        while(!newStack.empty()){
            oldStack.push(newStack.top());
            newStack.pop();
        }
    }

public:
    MyQueue() {
    }

    void push(int x) {
        newStack.push(x);
    }

    int pop() {
        moveToOld();
        int top = oldStack.top();
        oldStack.pop();
        return top;
    }

    int peek() {
        moveToOld();
        return oldStack.top();
    }

    bool empty() {
        return newStack.empty() && oldStack.empty();
    }

};
/*
newStack={3,2,1,0}
oldStack={}

當peek或pop時, moveToOld

newStack={}
oldStack={0,1,2,3}
*/