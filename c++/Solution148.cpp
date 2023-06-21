//LinkedList O(nlogn) O(logn)
class Solution148 {
public:
    ListNode* sortList(ListNode* head) {
        if(!head || !head->next) return head;

        ListNode* fast = head;
        ListNode* slow = head;
        ListNode* slowPre = head;
        while(fast && fast->next){
            slowPre = slow;
            fast = fast->next->next;
            slow = slow->next;
        }
        slowPre->next = nullptr;//斷

        ListNode* l = sortList(head);
        ListNode* r = sortList(slow);
        return merge(l, r);
    }

    ListNode* merge(ListNode* a, ListNode* b){
        ListNode* dummy = new ListNode(-1);
        ListNode* cur = dummy;
        while(a && b){
            if(a->val < b->val){
                cur->next = a;
                a = a->next;
            }else{
                cur->next = b;
                b = b->next;
            }
            cur = cur->next;
        }

        cur->next = a ? a : b;

        return dummy->next;
    }
};
/*
1 -> 2 -> 3 -> 4 -> null
f         f         f
s    s    s

1 -> 2 -> 3 -> null
f         f
s    s
*/