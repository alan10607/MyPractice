//LinkedList O(n) O(1)
class Solution234 {
public:
    bool isPalindrome(ListNode* head) {
        // 找到中點, 反轉中點後的另一半
        ListNode* fast = head;
        ListNode* slow = head;
        while (fast && fast->next) {
            fast = fast->next->next;
            slow = slow->next;
        }

        // 反轉slow之後全部的node
        ListNode* pre = nullptr;
        while (slow) {
            ListNode* next = slow->next;
            slow->next = pre;
            pre = slow;
            slow = next;
        }

        // 比較是否一樣
        while (head && pre) { //pre會較短, 參考下圖
            if (head->val != pre->val) return false;
            pre = pre->next;
            head = head->next;
        }
        return true;
    }
};
/* 本提follow up要求用O(1)space, 可以翻轉一半鏈表來比對
偶數:
f1        f2        f3
s1   s2   s3
1 -> 2 -> 2 -> 1 -> N

反轉後(偶數)
1 -> 2 -> 2 <- 1
          v
          N


奇數:
f1        f2        f3
s1   s2   s3
1 -> 2 -> 3 -> 2 -> 1 -> N

反轉後(奇數)
1 -> 2 -> 3 <- 2 <- 1
          v
          N
*/