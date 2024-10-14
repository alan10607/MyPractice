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
## Fast-Slow pointers
- https://leetcode.com/problems/remove-duplicates-from-sorted-array/
- https://leetcode.com/problems/remove-element/
- https://leetcode.com/problems/move-zeroes/

### In-Place Modification
```
void fastSlowPointer(vector<int>& nums) {
    int fast = 0, slow = 0;
    while (fast < nums.size()) {
        ...
    }
```


### Sliding Window


## L-R Pointers
- https://leetcode.com/problems/reverse-string/
- https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
- https://leetcode.com/problems/longest-palindromic-substring/
### Binary Search
### N-Sum
### Palindrome
```
string palindrome(int l, int r, string s) {
    while (0 <= l && r < s.length() && s[l] == s[r]) {
        --l;
        ++r;
    }
    ++l; // 抵銷最後一次
    --r; // 抵銷最後一次
    return s.substr(l, r - l + 1);
}
```


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


===

// Need Verify:
二分法小結
Reference:
https://medium.com/@CecileLiu/%E4%BA%8C%E5%88%86%E6%B3%95%E7%9A%84%E5%8D%80%E9%96%93-%E7%B6%B2%E8%B7%AF%E4%B8%8A%E6%9C%80%E8%A9%B3%E7%9B%A1%E7%9A%84%E8%A7%A3%E8%AA%AA-5adf3be3bc5e
https://blog.csdn.net/allenhsu6/article/details/113779530

1. 找某目標, 可以分成左閉右閉[l,r) 或左閉右開[l,r]
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) l = mid + 1;
        else r = mid;
    }
    return -1;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) l = mid + 1;
        else r = mid - 1;
    }
    return -1;
}


2. 找第一個不小於某目標的(lower_bound), ex:[0,1,1,1,1], target=1, return=1
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] < target) l = mid + 1;
        else r = mid;
    }
    return r;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (target <= nums[mid]) r = mid - 1
        else l = mid + 1;
    }
    return l;
}


3. 找第一個大於某目標的(upper_bound), ex:[0,1,1,1,1], target=1, return=5
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] <= target) l = mid + 1;
        else r = mid;
    }
    return r;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] <= target) l = mid + 1;
        else r = mid - 1;
    }
    return l;
}
