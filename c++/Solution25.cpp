//LinkedList O(n) O(1)
class Solution25 {
public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        ListNode* end = head;
        for (int i = 0; i < k; ++i) {
            if (!end) {
                return head;
            } else {
                end = end->next;
            }
        }

        ListNode* new_head = reverse(head, end);
        head->next = reverseKGroup(end, k); //此時head已經是最後一個
        return new_head;
    }

    ListNode* reverse(ListNode* start, ListNode* end) {
        ListNode* pre = end;
        ListNode* cur = start;
        while (cur != end) {
            ListNode* next = cur->next;
            cur->next = pre;
            pre = cur;
            cur = next;
        }
        return pre; // 此時變成第一個了
    }
};
/* 這題也可用recursive的方式做(類似24題), if k=3
1 -> 2 -> 3 -> 4
start          end
cur            pre

---------------v
1    2 -> 3 -> 4
pre  cur


---------------v
1 <- 2    3 -> 4
     pre  cur

---------------v
1 <- 2 <- 3    4
          pre  cur(此時cur=end, 回傳pre作為new_head)
*/

//LinkedList O(n) O(1)
class Solution25_2 {
public:
    ListNode* reverseKGroup(ListNode* head, int k) {
        ListNode* dummy = new ListNode(-1);
        dummy->next = head;
        ListNode* start = dummy;
        ListNode* end = head;
        for(int i =1  ;end;  ++i){
            end = end->next;
            if (i % k == 0) {
                start = reverse(start, end);//翻轉start~end間(皆不包含)
            }
        }
        return dummy->next;
    }

    ListNode* reverse(ListNode* start, ListNode* end) {
        ListNode* cur = start->next;
        ListNode* pre = end;
        ListNode* next_start = start->next;//用來回傳
        while (cur != end) {
            ListNode* next = cur->next;
            cur->next = pre;
            pre = cur;
            cur = next;
        }
        start->next = pre;

        return next_start;
    }
};

/* if k=3
-1 -> 1 -> 2 -> 3 -> 4 -> 5
 start               end
      next_start
      cur            pre

      ---------------v
-1 -> 1    2 -> 3 -> 4 -> 5
      pre  cur

      ---------------v
-1 -> 1 <- 2    3 -> 4 -> 5
           pre  cur

      ---------------v
-1 -> 1 <- 2 <- 3    4 -> 5
                pre  cur(此時cur=end, 離開while)

      ---------------v
-1    1 <- 2 <- 3    4 -> 5
 ---------------^
 start          pre

start接回pre, 並回傳next_start作為下次的start

*/