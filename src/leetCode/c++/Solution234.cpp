//LinkedList O(n) O(1)
class Solution234 {
public:
    bool isPalindrome(ListNode* head) {
        //find mid
        ListNode* fast = head;
        ListNode* slow = head;
        while(fast && fast->next){
            fast = fast->next->next;
            slow = slow->next;xs
        }

        //reverse second list
        ListNode* pre = nullptr;
        while(slow){
            ListNode* next = slow->next;
            slow->next = pre;
            pre = slow;
            slow = next;
        }

        //compare
        while(pre){//pre會較短, head還連著
            if(head->val != pre->val) return false;
            head = head->next;
            pre = pre->next;
        }
        return true;
    }
};