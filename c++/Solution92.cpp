//LinkedList O(n) O(1)
class Solution92 {
public:
    ListNode* reverseBetween(ListNode* head, int left, int right) {
        ListNode* dummy = new ListNode(-1, head);
        ListNode* cur = dummy;
        ListNode *start, *end;
        for(int i=0; i<=right; ++i){//1 <= left <= right <= n
            if(i == left - 1) start = cur;
            if(i == right) end = cur->next;
            cur = cur->next;
        }

        reverse(start, end);//倒轉start~end, 不含start與end本身
        return dummy->next;
    }

    void reverse(ListNode* start, ListNode* end){
        ListNode* pre = end;
        ListNode* cur = start->next;
        while(cur != end){
            ListNode* next = cur->next;
            cur->next = pre;
            pre = cur;
            cur = next;
        }
        start->next = pre;
    }
};
/* head = [1,2,3,4,5], left = 2, right = 4

-1 -> 1 -> 2 -> 3 -> 4 -> 5 -> N
           ^cur           ^pre

-1 -> 1 -> 2    3 -> 4 -> 5 -> N
           ---------------^
           ^pre ^cur

-1 -> 1 -> 2 <- 3    4 -> 5 -> N
           ---------------^
                ^pre ^cur

-1 -> 1 -> 2 <- 3 <- 4    5 -> N
           ---------------^
                     ^pre ^cur


      ---------------v
-1 -> 1    2 <- 3 <- 4    5 -> N
           ---------------^

*/