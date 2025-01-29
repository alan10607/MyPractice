//Heap MedianFinder(), findMedian(): O(1) O(n), addNum(): O(logn) O(n)
class MedianFinder {//Solution295
public:
    priority_queue<int, vector<int>, less<int>> max_heap; // 大到小
    priority_queue<int, vector<int>, greater<int>> min_heap; // 小到大

    MedianFinder() {}

    void addNum(int num) {
        max_heap.push(num); // 這裡一定要混合兩個heap, 不然兩個heap不會有中位數在top
        min_heap.push(max_heap.top());
        max_heap.pop();
        if (max_heap.size() < min_heap.size()) { // 保持max > min
            max_heap.push(min_heap.top());
            min_heap.pop();
        }
    }

    double findMedian() {
        if (max_heap.size() > min_heap.size()) {
            return max_heap.top();
        } else { // max_heap.size() == min_heap.size()
            return (max_heap.top() + min_heap.top()) / 2.0;
        }
    }
};
/*
max_heap    min_heap

   -----------
   |         v
| 小 |     | 大 |
| .. |     | .. |
|    |     |    |
|最小|      |最大|
+----+     +----+

*/