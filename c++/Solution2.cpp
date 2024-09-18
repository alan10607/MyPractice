//LinkedList O(max(m, n)) O(1), m, n表示兩鏈表長度
class Solution2 {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode(-1);
        ListNode* head = dummy;
        int carry = 0;
        while(l1 || l2 || carry) {//loop till carry and all listNodes are null
            int v1 = l1 ? l1->val : 0;
            int v2 = l2 ? l2->val : 0;
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            head->next = new ListNode(sum % 10);

            head = head->next;
            if(l1) {
                l1 = l1->next;
            }
            if(l2) {
                l2 = l2->next;
            }
        }

        return dummy->next;
    }
};