//LinkedList O(n) O(1)
class Solution25 {
public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* start = dummy;
        ListNode* cur = start->next;
        for(int i=1; cur; ++i){
            cur = cur->next;
            if(i % k == 0)
                start = reverse(start, cur);//翻轉start~end間(皆不包含)

        }
        return dummy->next;
    }

    ListNode* reverse(ListNode* start, ListNode* end) {
        ListNode* pre = end;
        ListNode* cur = start->next;
        ListNode* nextStart = start->next;//用來回傳
        while(cur != end){
            ListNode* next = cur->next;
            cur->next= pre;
            pre = cur;
            cur = next;
        }
        start->next = pre;

        return nextStart;
    }
};
/*
-1 -> 1 -> 2 -> 3 -> 4
 s    c
 s              c=end

 ----------v
-1    1 <- 2    3 -> 4
      ----------^
      s         c

*/