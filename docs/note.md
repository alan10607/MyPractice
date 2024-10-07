# Array / Hashing

# Linked List

https://leetcode.com/problems/merge-two-sorted-lists/
https://leetcode.com/problems/partition-list/
https://leetcode.com/problems/merge-k-sorted-lists/description/
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
https://leetcode.com/problems/middle-of-the-linked-list/
https://leetcode.com/problems/linked-list-cycle/
https://leetcode.com/problems/linked-list-cycle-ii/
https://leetcode.com/problems/intersection-of-two-linked-lists/
# Two Pointers
## Fast and Slow pointers
# Sliding Window
# Binary Search
# Dynamic Programming
## Knapsack Problem
## Greedy
# Backtracking
## DFS
## BFS
# Binary Tree
# Subset / Combination / Permutation
# Intervals
# Math / Bit

# C++ Basci
sort跟priority_queue剛好相反
## 有關排序
### Sort
#### 小到大 (default)
```
sort(vec.begin(), vec.end());
sort(vec.begin(), vec.end(), less<int>());

auto comp1 = [](int a, int b){return a < b; };
sort(vec.begin(), vec.end(), comp1);
```
#### 大到小
```
sort(vec.begin(), vec.end(), greater<int>());

auto comp2 = [](int a, int b){return a > b; };
sort(vec.begin(), vec.end(), comp2);
```

### Priority Queue
#### 大到小, max heap (default)
```
priority_queue<int, vector<int>> pq;
priority_queue<int, vector<int>, less<int>> pq1;

auto comp1 = [](int a, int b){return a < b; };
priority_queue<int, vector<int>, decltype(comp1)> pq2(comp1);
```
#### 小到大, min heap

```
priority_queue<int, vector<int>, greater<int>> pq3;

auto comp2 = [](int a, int b){return a > b; };
priority_queue<int, vector<int>, decltype(comp2)> pq4(comp2);
```