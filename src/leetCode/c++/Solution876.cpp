//LinkedList O(n) O(1)
class Solution876 {
public:
    ListNode* middleNode(ListNode* head) {
        //if two middle return the second one, 所以不用再加dummy了
        ListNode* fast = head;
        ListNode* slow = head;

        while(fast && fast->next){
            fast = fast->next->next;
            slow = slow->next;
        }
        return slow;
    }
};
/*
 1 -> 2 -> 3 -> 4 -> 5 -> null
f/s
      s    f
           s         f

 1 -> 2 -> 3 -> 4 -> null
f/s
      s    f
           s         f
*/