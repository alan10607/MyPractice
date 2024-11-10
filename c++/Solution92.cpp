//LinkedList O(n) O(1)
class Solution92 {
public:
    ListNode* reverseBetween(ListNode* head, int left, int right) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* start = dummy;
        for (int i = 1; i < left; ++i) {
            start = start->next;
        }
        start->next = reverseNth(start->next, right - left + 1);
        return dummy->next;
    }

    ListNode* reverseNth(ListNode* head, int n) {
        ListNode* cur = head;
        ListNode* pre = nullptr;

        for (int i = 0; i < n; ++i) {
            ListNode* next = cur->next;
            cur->next = pre;
            pre = cur;
            cur = next;
        }

        head->next = cur; // 原本的開頭接到下一個
        return pre;
    }
};


//LinkedList O(n) O(n), stack有n層需要空間O(n)
class Solution92_2 {
public:
    ListNode* end = nullptr;

    ListNode* reverseBetween(ListNode* head, int left, int right) {
        if (left == 1) {
            return reverseNth(head, right);
        }
        head->next = reverseBetween(head->next, left - 1, right - 1);
        return head;
    }

    ListNode* reverseNth(ListNode* head, int n) {
        if (n == 1) {
            end = head->next; // 標記end給最一開始的head用
            return head;
        }

        ListNode* last = reverseNth(head->next, n - 1);
        head->next->next = head;
        head->next = end; // 如果不是開頭的head, 這個步驟會被前stack function中的head->next->next = head更新
        return last;
    }
};
/* 也可以用recursion做
reverseNth: 把第n個node變成head, 反轉之前
ex: ListNode=[1,2,3,4,5], n=3, 則res=[3,2,1,4,5]


*/


//LinkedList O(n) O(1)
class Solution92_3 {
public:
    ListNode* reverseBetween(ListNode* head, int left, int right) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* cur = dummy;
        ListNode *start, *end;
        for (int i = 0; i <= right; ++i) { // 1 <= left <= right <= n
            if (i == left - 1) start = cur;
            if (i == right) end = cur->next;
            cur = cur->next;
        }

        reverse(start, end); // 倒轉start~end, 不含start與end本身
        return dummy->next;
    }

    void reverse(ListNode* start, ListNode* end) {
        ListNode* pre = end;
        ListNode* cur = start->next;
        while (cur != end) {
            ListNode* next = cur->next;
            cur->next = pre;
            pre = cur;
            cur = next;
        }
        start->next = pre;
    }
};
/* head = [1,2,3,4,5], left = 2, right = 4

-1 -> 1 -> 2 -> 3 -> 4 -> 5 -> N
           ^cur           ^pre

-1 -> 1 -> 2    3 -> 4 -> 5 -> N
           ---------------^
           ^pre ^cur

-1 -> 1 -> 2 <- 3    4 -> 5 -> N
           ---------------^
                ^pre ^cur

-1 -> 1 -> 2 <- 3 <- 4    5 -> N
           ---------------^
                     ^pre ^cur


      ---------------v
-1 -> 1    2 <- 3 <- 4    5 -> N
           ---------------^

*/