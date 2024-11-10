//LinkList O(n) O(1)
class Solution206 {
public:
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
};


//LinkList O(n) O(n)
class Solution206_2 {
public:
    ListNode* reverseList(ListNode* head) {
        if (!head || !head->next)
            return head;

        ListNode* last = reverseList(head->next);
        head->next->next = head;
        head->next = nullptr;
        return last;
    }
};
/* Follow up 是用recursively做(效能較差)
vhead
1 -> 2 -> 3 -> 4 -> null

ListNode* last = reverseList(head->next);後
vhead
1 -> 2 <- 3 <- 4
               ^last

head->next->next = head;後
vhead
1 <- 2 <- 3 <- 4
               ^last

head->next = nullptr;後
        vhead
null <- 1 <- 2 <- 3 <- 4
                       ^last
*/