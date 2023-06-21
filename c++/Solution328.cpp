//LinkedList O(n) O(1)
class Solution328 {
public:
    ListNode* oddEvenList(ListNode* head) {
        if(!head) return head;

        ListNode* odd = head;
        ListNode* even = head->next;
        ListNode* evenHead = even;
        while(even && even->next){//確認第2與第3個存在
            odd->next = even->next;//1->3
            odd = odd->next;
            even->next = odd->next;//2->4
            even = even->next;
        }
        odd->next = evenHead;
        return head;
    }
};
/*
1 -> 2 -> 3 -> 4 -> 5
1 -> 3 -> 5
2 -> 4 -> N

*/