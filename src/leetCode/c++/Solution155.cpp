//Stack MinStack(), push(), pop(), top(), getMin(): O(1) O(n)
class MinStack {//Solution155
public:
    stack<int> st;
    stack<int> minSt;//當前最小的值存入這裡

    MinStack() {
    }

    void push(int val) {
        st.push(val);
        int minVal = minSt.empty() ? val : min(val, minSt.top());
        minSt.push(minVal);
    }

    void pop() {
        st.pop();
        minSt.pop();
    }

    int top() {
        return st.top();
    }

    int getMin() {
        return minSt.top();
    }
};