//LinkedList O(max(m, n)) O(1), m, n表示兩鏈表長度
class Solution2 {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode(-1);
        ListNode* cur = dummy;
        int carry = 0;
        while(l1 || l2){
            int val1 = l1 ? l1->val : 0;
            int val2 = l2 ? l2->val : 0;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            cur->next = new ListNode(sum % 10);

            cur = cur->next;
            if(l1) l1 = l1->next;
            if(l2) l2 = l2->next;
        }

        if(carry > 0) cur->next = new ListNode(carry);//記得加入最後
        return dummy->next;
    }
};