//LinkedList O(n) O(1)
class Solution86 {
public:
    ListNode* partition(ListNode* head, int x) {
        // should preserve order in each of the two partitions
        ListNode* small_dummy = new ListNode(-1);
        ListNode* big_dummy = new ListNode(-1);
        ListNode* small = small_dummy;
        ListNode* big = big_dummy;

        while (head) {
            if (head->val < x) {
                small->next = head;
                small = small->next;
            } else {
                big->next = head;
                big = big->next;
            }
            ListNode* next = head->next; //斷開連結, 否則有可能成環
            head->next = nullptr;
            head = next;
        }

        small->next = big_dummy->next;
        
        return small_dummy->next;
    }
};