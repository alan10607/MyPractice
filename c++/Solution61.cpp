//LinkedList O(n) O(1)
class Solution61 {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        if(!head) return head;

        ListNode* cur = head;
        int len = 1;
        while(cur->next){
            ++len;
            cur = cur->next;
        }
        cur->next = head;//成環

        int skip = len - (k % len) - 1;
        cur = head;
        for(int i=0; i<skip; ++i)
            cur = cur->next;

        ListNode* newHead = cur->next;
        cur->next = nullptr;
        return newHead;
    }
};
/*
k       1   2   3   4   5   len-(k%len)-1
1       5   1   2   3   4   3
2       4   5   1   2   3   2
3       3   4   5   1   2   1
4       2   3   4   5   1   0
5       1   2   3   4   5   4

*/