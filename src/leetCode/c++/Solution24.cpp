//LinkedList O(n) O(1)
class Solution24 {
public:
    ListNode* swapPairs(ListNode* head) {
        if(!head || !head->next) return head;

        ListNode* next = head->next;
        head->next = swapPairs(next->next);
        next->next = head;
        return next;//已經變成第一個了
    }
};