//LinkedList O(n) O(1)
class Solution25 {
public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* start = dummy;
        ListNode* cur = start->next;
        for(int i=1; cur; ++i){
            if(i % k == 0){
                start = reverse(start, cur->next);
                cur = start->next;
            }else{
                cur = cur->next;
            }
        }
        return dummy->next;
    }

    ListNode* reverse(ListNode* start, ListNode* end) {
        ListNode* pre = end;//先設pre為下一組的起點
        ListNode* cur = start->next;
        ListNode* nextStart = start->next;//用來回傳
        while(cur != end){
            ListNode* next = cur->next;
            cur->next = pre;
            pre = cur;
            cur = next;
        }
        start->next = pre;//記得start接回

        return nextStart;
    }
};