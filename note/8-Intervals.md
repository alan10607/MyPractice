# Intervals
- https://leetcode.com/problems/merge-intervals/
- https://leetcode.com/problems/insert-interval/
- https://leetcode.com/problems/non-overlapping-intervals/
- *https://leetcode.com/problems/meeting-rooms/
- *https://leetcode.com/problems/meeting-rooms-ii/
- https://leetcode.com/problems/minimum-interval-to-include-each-query/

1. 排序interval
- 依照start排序:
    - merge, select 判斷重疊
    - 按照區間開始的時間，從左到右處理
    
- 依照end排序: 
    - 優先處理 最早結束 的 interval
    - 常見於Greedy, Interval Scheduling, 在固定範圍內, 如何選出最多個互不重疊的interval

- start, end分開排序:
    - Sweep Line, Two Pointers
    - 用來追蹤某個時間點目前有多少個 interval 正在進行, 例如 meeting-rooms-ii


2. 判斷重疊
兩個 interval: A = [a, b], B = [c, d]
- 在 a <= d && c <= b 的情況會接觸重疊
- 如果A,B按照start排序, 則 a <= d 必成立, 只要考慮 c <= b
```cpp
//c <= b:
A: a --------- (b)
B:      (c) ---------- d

// or
A: a ---------------- (b)
B:      (c) --- d

//a <= d :
A: c --------- (d)
B:      (a) ---------- b
// or
A: c ---------------- (d)
B:      (a) --- b
```