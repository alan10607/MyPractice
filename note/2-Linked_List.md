# Linked List
- https://leetcode.com/problems/merge-two-sorted-lists/
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


## 快慢指標 -> 查是否有交點
```cpp
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


## 反轉鏈表
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
