//LinkedList O(n) O(1)
class Solution203 {
public:
    ListNode* removeElements(ListNode* head, int val) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* cur = dummy;
        while(cur->next){
            if(cur->next->val == val){
                ListNode* remove = cur->next;
                cur->next = cur->next->next;
                delete remove;//正常來說要delete
            }else{
                cur = cur->next;
            }
        }
        return dummy->next;
    }
};