//Fast & Slow Pointer O(n) O(1)
class Solution19 {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        //n <= length of ListNode
        ListNode* fast = head;
        ListNode* slow = head;

        for(int i=0; i<n; ++i)
            fast = fast->next;

        if(!fast) return head->next;//代表是移除第一個

        while(fast->next){//要讓slow停在要移除的那個之前, 所以是fast->next==nullptr時
            fast = fast->next;
            slow = slow->next;
        }

        slow->next = slow->next->next;
        return head;
    }
};
/* head = [1,2,3,4,5], n = 2

 *1 -> 2 -> 3 -> 4 -> 5 -> null
            ^s        ^f

*/