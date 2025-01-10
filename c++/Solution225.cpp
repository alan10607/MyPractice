//Stack push(): O(n) O(n), pop(), top(), empty(): O(1) O(n), push時需要前移O(n)
class MyQueue {
class MyStack {//Solution225
public:
    queue<int> q;

    MyStack() {}

    void push(int x) {
        q.push(x);
        int size = q.size();
        for (int i = 0; i < size - 1; ++i) { // 迴轉留下一個, 就是把x放到最前面
            q.push(q.front());
            q.pop();
        }
    }

    int pop() {
        int val = q.front();
        q.pop();
        return val;
    }

    int top() {
        return q.front();
    }

    bool empty() {
        return q.empty();
    }
};
/*
All the calls to pop and top are valid.
所以不考慮在空的時候pop()或top()

每次push的時候都把q向前移動size-1個, 移出queue的重新塞回隊尾(可以想成迴轉)
push(1), q={1}
push(2), q={1,2}, 前移一格後q={2,1}
push(3), q={2,1,3}, 前移兩格後q={3,2,1}
push(4), q={3,2,1,4}, 前移三格後q={4,3,2,1}

*/