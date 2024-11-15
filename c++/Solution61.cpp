//LinkedList O(n) O(1)
class Solution61 {
public:
    ListNode* rotateRight(ListNode* head, int k) {
        if (!head)
            return head;

        ListNode* cur = head;
        int len = 1; // 計算總長度
        while (cur->next) {
            ++len;
            cur = cur->next;
        }
        cur->next = head; // 成環

        int skip = len - (k % len) - 1; // 需要的偏移量
        for (int i = 0; i < skip; ++i) {
            head = head->next;
        }
        ListNode* res = head->next;
        head->next = nullptr;
        return res;
    }
};
/*
假設長度len = 5
1 -> 2 -> 3 -> 4 -> 5, k%len=0, 從head往前4 node是新的尾巴
5 -> 1 -> 2 -> 3 -> 4, k%len=1, 從head往前3 node是新的尾巴
4 -> 5 -> 1 -> 2 -> 3, k%len=2, 從head往前2 node是新的尾巴
3 -> 4 -> 5 -> 1 -> 2, k%len=3, 從head往前1 node是新的尾巴
2 -> 3 -> 4 -> 5 -> 1, k%len=4, 從head往前0 node是新的尾巴

需要從head往前len-(k%len)-1找到新的尾巴斷開
*/