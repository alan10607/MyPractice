# Array / Hashing
- https://leetcode.com/problems/range-addition-ii/
- https://leetcode.com/problems/two-sum/
- *https://leetcode.com/problems/two-sum-iii-data-structure-design/
- https://leetcode.com/problems/flatten-nested-list-iterator/
- https://leetcode.com/problems/insert-delete-getrandom-o1/
- https://leetcode.com/problems/random-pick-with-blacklist/
- https://leetcode.com/problems/longest-common-prefix/
- https://leetcode.com/problems/map-sum-pairs/
- https://leetcode.com/problems/maximize-distance-to-closest-person/
- https://leetcode.com/problems/exam-room/

## Matrix 矩陣
- https://leetcode.com/problems/rotate-image
- https://leetcode.com/problems/spiral-matrix/
- https://leetcode.com/problems/spiral-matrix-ii/

```cpp
void matrixSolution(vector<vector<int>>& matrix) {
    int t = 0; // top, bottom, left, right定位四個方向
    int b = matrix.size() - 1;
    int l = 0;
    int r = matrix[0].size() - 1;
    while(...){
        ...
    }
}
```
```cpp
/* 畫圖來看, 
t/b是上下, l/r是左右, matrix[t/b, l/r]

        left                right
        +-----+-----+-----+-----+
 top->  |[t,l]|     |     |[t,r]|
        +-----+-----+-----+-----+
        |     |     |     |     |
        +-----+-----+-----+-----+
bottom->|[b,r]|     |     |[b,r]|
        +-----+-----+-----+-----+


各方向加上for loop, i 變量的話

        ---> [t][l+i]
                          |
    ^       1   2   3     v
    |       4   5   6   [t+i][r]
[b-i][l]    7   8   9

            <--- [b][r-i]


*/
```


## Prefix Sum 前綴和
- https://leetcode.com/problems/range-sum-query-immutable/
- https://leetcode.com/problems/range-sum-query-2d-immutable/
- https://leetcode.com/problems/subarray-sum-equals-k/
- https://leetcode.com/problems/random-pick-with-weight/

//TO-DO
- https://leetcode.com/problems/contiguous-array/
- https://leetcode.com/problems/random-pick-with-weight/
- https://leetcode.com/problems/product-of-array-except-self/

https://leetcode.com/problems/minimum-size-subarray-sum/
*https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
https://leetcode.com/problems/continuous-subarray-sum/
https://leetcode.com/problems/subarray-product-less-than-k/
https://leetcode.com/problems/find-pivot-index/
https://leetcode.com/problems/subarray-sums-divisible-by-k/
https://leetcode.com/problems/max-consecutive-ones-iii/
https://leetcode.com/problems/matrix-block-sum/
```cpp
當需要計算array某區間的和時, 可以透過不斷累加做為上升值
不同上升值相減就可以得到區間和


index   0     1     2     3
nums=   1     2     3     4
        |     |     |     |
        v     v     v     v
sums=   0 --> 1 --> 3 --> 6 --> 10   
計算          0+1   1+2   3+3   6+4


```


## Difference Array 差分法
- $ https://leetcode.com/problems/range-addition/
- https://leetcode.com/problems/corporate-flight-bookings/
- https://leetcode.com/problems/car-pooling/description/
```cpp
當array在區間內有頻繁的增減, 可以直接計算區間變化來獲得array
diff代表區間的變化量, 透過計算區間變化量可以得到值(捨棄區間頭尾, 頭尾會是起始的0)

index   0     1     2     3     4     5
diff=   8     -6    4    -3    -2    -1
        |   ^ |   ^ |   ^ |   ^ |
        v  /  v  /  v  /  v  /  v
nums=   8     2     6     3     1
計算    0+8   8-6   2+4   6-3   3-2

    
```

## Stack & Queue
- https://leetcode.com/problems/implement-queue-using-stacks/
- https://leetcode.com/problems/implement-stack-using-queues/


### Monotonic Stack / Queue
- https://leetcode.com/problems/next-greater-element-i/
- https://leetcode.com/problems/next-greater-element-ii/
- https://leetcode.com/problems/daily-temperatures/
- https://leetcode.com/problems/sliding-window-maximum


1. Monotonic Stack
```cpp
stack: push覆蓋掉比當前value小的

前              後
                |
        |       |
        |   |   |
    |   |   |   |
   top      ^
            remove

```
```cpp
// 判斷array中右邊第一個比自己大的數, ex: nums=[2,1,2,4,3], res=[4,2,4,-1,-1]
void monotonicStack(vector<int>& nums) {
    stack<int> st;

    for (int num : nums) {
        while (!st.empty() && st.top() < num) {
            st.pop();  // 移除比這個小的, 使st保持遞增
        }
        st.push(num);

        透過st.top()做處理...
    }
}
```

2. Monotonic Queue
```cpp
deque: push_back覆蓋掉比當前value小的

前              後
    |
    |       |
    |   |   |
    |   |   |   |
front   ^       
        remove


```
```cpp
vector<int> monotonicDeque(vector<int>& nums) {
    deque<int> q;

    for (int num : nums) { // 倒著進入
        if(...) {
            q.pop_front();
        }

        while (!q.empty() && q.back() < num) {
            q.pop_back();  // 移除比這個小的, 使st保持遞減(從後面移除所以遞減)
        }
        q.push_back(num);

        透過q.front()做處理...
    }
}
```

### Calculator
- https://leetcode.com/problems/basic-calculator/
- https://leetcode.com/problems/basic-calculator-ii/
- https://leetcode.com/problems/basic-calculator-iii/


## Heap
- https://leetcode.com/problems/top-k-frequent-words/
- https://leetcode.com/problems/design-twitter/