//Fast & Slow Pointer Floyd Cycle Detection Algorithm O(n) O(1)
class Solution142 {
public:
    ListNode *detectCycle(ListNode *head) {
        ListNode* fast = head;
        ListNode* slow = head;
        while(true){
            if(!fast || !fast->next) return nullptr;//if no cycle, return null
            fast = fast->next->next;
            slow = slow->next;
            if(fast == slow) break;
        }

        ListNode* start = head;
        while(start != slow){
            start = start->next;
            slow = slow->next;
        }
        return start;
    }
};
/* head = [3,2,0,-4], pos = 1
第一次loop判斷是否成環
fast=3->0->2->-4
slow=3->2->0->-4

第二次判斷成環位置
fast=-4->2
slow= 3->2

res=2
*/