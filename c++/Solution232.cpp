//Stack push(), pop(), peek(), empty(): O(1) O(n), 但移動stack時需要O(n)時間
class MyQueue {//Solution232
public:
    stack<int> st;
    stack<int> reversedSt;

    MyQueue() {}

    void push(int x) { st.push(x); }

    int pop() {
        peek();
        int val = reversedSt.top();
        reversedSt.pop();
        return val;
    }

    int peek() {
        if (reversedSt.empty()) { // 空的時候才加入, 否則順序會亂掉
            while (!st.empty()) {
                int val = st.top();
                st.pop();
                reversedSt.push(val);
            }
        }

        return reversedSt.top();
    }

    bool empty() {
        return st.empty() && reversedSt.empty();
    }
};
/*
All the calls to pop and peek are valid.
所以不考慮在空的時候pop()或peek()

建立兩個stack
stack={3,2,1,0}
reversed={}

當要peek或pop的時候把st拉出放到reversedSt
stack={}
reversed={0,1,2,3}
*/