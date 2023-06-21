//LinkedList O(n) O(1)
class Solution143 {
public:
    void reorderList(ListNode* head) {
        //find mid
        ListNode* fast = head;
        ListNode* slow = head;
        while(fast->next && fast->next->next){//偶數時找到中左那個就好
            fast = fast->next->next;
            slow = slow->next;
        }
        ListNode* cur = slow->next;
        slow->next = nullptr;//斷開

        //reverse
        ListNode* pre = nullptr;
        while(cur){
            ListNode* next = cur->next;
            cur->next = pre;
            pre = cur;
            cur = next;
        }

        //merge
        while(head && pre){
            ListNode* nextA = head->next;
            head->next = pre;
            head = nextA;
            ListNode* nextB = pre->next;
            pre->next = head;
            pre = nextB;
        }
    }
};
/*
1 -> 2 -> 3 -> 4 -> 5 -> null
f         f         f
s    s    s

1 -> 2 -> 3 -> 4 -> null
f         f
s    s

*/