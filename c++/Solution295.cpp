//Heap MedianFinder(), findMedian(): O(1) O(n), addNum(): O(logn) O(n)
class MedianFinder {//Solution295
public:
    priority_queue<int, vector<int>, less<int>> small;//大到小
    priority_queue<int, vector<int>, greater<int>> big;//小到大

    MedianFinder() {
    }

    void addNum(int num) {
        small.push(num);
        big.push(small.top());
        small.pop();//排列之後把比較大的傳到big
        if(small.size() < big.size()){//但要保持small>=big
            small.push(big.top());
            big.pop();
        }
    }

    double findMedian() {
        return small.size() > big.size() ? small.top() : (big.top() + small.top()) / 2.0;
    }
};
/*
small     big

   ---------
   |       v
| 小 |   | 大 |
| .. |   | .. |
|    |   |    |
|最小|    |最大|
+----+   +----+

*/