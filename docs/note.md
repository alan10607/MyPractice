# Array / Hashing

# Linked List
- https://leetcode.com/problems/merge-two-sorted-lists/
- https://leetcode.com/problems/partition-list/
- https://leetcode.com/problems/merge-k-sorted-lists/description/
- https://leetcode.com/problems/remove-nth-node-from-end-of-list/
- https://leetcode.com/problems/middle-of-the-linked-list/
- https://leetcode.com/problems/linked-list-cycle/
- https://leetcode.com/problems/linked-list-cycle-ii/
- https://leetcode.com/problems/intersection-of-two-linked-lists/
- https://leetcode.com/problems/remove-duplicates-from-sorted-list/


## 鏈表合併/分割/修改
```
ListNode* foo(ListNode* list) {
    ListNode* dummy = new ListNode(-1); // 用來定位起點
    ListNode* tail = dummy;             // 用來指向當前位置

    while (某條件) {
        ...

        tail = tail->next;
    }

    return dummy->next;
}
```

## 快慢指標 -> 查是否有交點
```
bool foo(ListNode* head) {
    ListNode* fast = head;
    ListNode* slow = head;
    while (fast && fast->next) { //條件通常是判斷fast, 且判斷兩層node
        fast = fast->next->next;
        slow = slow->next;
        ...
    }
}
```

# Two Pointers
## Fast-Slow pointer
### In-Place Modify
- https://leetcode.com/problems/remove-duplicates-from-sorted-array/
- https://leetcode.com/problems/remove-element/
- https://leetcode.com/problems/move-zeroes/
### Sliding Window

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