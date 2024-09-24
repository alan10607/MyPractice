//LinkedList O(n) O(1)
class Solution24 {
public:
    ListNode* swapPairs(ListNode* head) {
        if(!head || !head->next) return head;

        ListNode* second = head->next;
        head->next = swapPairs(second->next);
        second->next = head;
        return second;//已經變成第一個了
    }
};


//LinkedList O(n) O(1)
class Solution24_2 {
public:
    ListNode* swapPairs(ListNode* head) {
        ListNode* dummy = new ListNode(-1);
        dummy->next = head;
        ListNode* tail = dummy;
        while (tail->next && tail->next->next) {
            ListNode* a = tail->next;
            ListNode* b = tail->next->next;
            a->next = a->next->next;
            b->next = a;
            tail->next = b;
            tail = a;
        }
        return dummy->next;
    }
};