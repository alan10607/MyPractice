# Linked List
- https://leetcode.com/problems/merge-two-sorted-lists/
- https://leetcode.com/problems/reorder-list/
- https://leetcode.com/problems/copy-list-with-random-pointer/
- https://leetcode.com/problems/add-two-numbers/
- https://leetcode.com/problems/find-the-duplicate-number/
- https://leetcode.com/problems/partition-list/
- https://leetcode.com/problems/merge-k-sorted-lists/
- https://leetcode.com/problems/remove-nth-node-from-end-of-list/
- https://leetcode.com/problems/middle-of-the-linked-list/
- https://leetcode.com/problems/linked-list-cycle/
- https://leetcode.com/problems/linked-list-cycle-ii/
- https://leetcode.com/problems/intersection-of-two-linked-lists/
- https://leetcode.com/problems/remove-duplicates-from-sorted-list/
- https://leetcode.com/problems/reverse-linked-list/
- https://leetcode.com/problems/reverse-linked-list-ii/
- https://leetcode.com/problems/reverse-nodes-in-k-group/
- https://leetcode.com/problems/palindrome-linked-list/
- https://leetcode.com/problems/rotate-list/
- https://leetcode.com/problems/lru-cache/
- https://leetcode.com/problems/lfu-cache/


## 鏈表合併/分割/修改
```cpp
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

## 快慢指標 -> 查是否成環
```cpp
bool hasCycle(ListNode* head) {
    ListNode* fast = head;
    ListNode* slow = head;
    while (fast && fast->next) { // fast往前兩步, 所以要確保fast和fast->next都存在
        fast = fast->next->next;
        slow = slow->next;
        ...
    }
}
```
### Floyd Cycle Detection Algorithm
```cpp
int findDuplicate(vector<int>& nums) {
    int fast = 0, slow = 0;
    do{
        fast = nums[nums[fast]]; // 快慢指標
        slow = nums[slow];
    }while(fast != slow);

    int start = 0;
    while(start != slow){ // 另一個從頭開始同時前進
        start = nums[start];
        slow = nums[slow];
    }
    return start;
}
```

## 快慢指標 -> 找中點
```cpp
bool findMid(ListNode* head) {
    ListNode* fast = head;
    ListNode* slow = head;
    while (fast && fast->next) { // fast往前兩步, 所以要確保fast和fast->next/都存在
    // 或是 while (fast->next && fast->next->next)
        fast = fast->next->next;
        slow = slow->next;
    }
    return slow;
}
```
```cpp
1  ->  2  ->  3  ->  4
f=s

1  ->  2  ->  3  ->  4
       s      f

1  ->  2  ->  3  ->  4
              s             f

while (fast && fast->next) -> 偶數時s為中點(靠右)
while (fast->next && fast->next->next) -> 偶數時s為中點(靠左)
```
```cpp
1  ->  2  ->  3  ->  4  ->  5
f=s

1  ->  2  ->  3  ->  4  ->  5
       s      f

1  ->  2  ->  3  ->  4  ->  5
              s             f

期數時s為中點
```

## 反轉鏈表 -> 單次反轉
```cpp
ListNode* reverseList(ListNode* head) {
    ListNode* cur = head;
    ListNode* pre = nullptr;
    while (cur) {
        ListNode* next = cur->next;
        cur->next = pre;
        pre = cur;
        cur = next;
    }
    return pre;
}
```

## 反轉鏈表 -> 分段反轉
```cpp
ListNode* reverse(ListNode* start, ListNode* end) { // 反轉區間是(start, end)左開右開
    ListNode* cur = start->next;
    ListNode* pre = end;
    ListNode* next_head = cur; // 用來回傳
    while (cur != end) {
        ListNode* next = cur->next;
        cur->next = pre;
        pre = cur;
        cur = next;
    }
    start->next = pre;
    return next_head; // 依照需求回傳, 像這裡是回傳下一個reserve的start node
}

```
```cpp
       next_head
          cur                   pre
start  ->  A  ->  B  ->  C  ->  end


第一次while:
       next_head
          pre    cur
start  ->  A      B  ->  C  ->  end
           |---------------------^


第二次while:
       next_head
                 pre    cur
start  ->  A  <-  B      C  ->  end
           |---------------------^

第二次while:
       next_head
                        pre     cur
start  ->  A  <-  B  <-  C      end
           |---------------------^

離開while後:
       next_head        pre     cur
  |----------------------v
start      A  <-  B  <-  C      end
           |---------------------^


此時(start,end)已經完成反轉
下一次起點的start為A, 可以用於下一個區間
```