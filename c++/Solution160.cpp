//LinkedList O(n) O(1)
class Solution160 {
public:
    ListNode* getIntersectionNode(ListNode* headA, ListNode* headB) {
        ListNode* tailA = headA;
        ListNode* tailB = headB;

        while (tailA != tailB) {
            if (!tailA) {//把null視為一個node再轉跳, 就可以包涵兩條ListNode不相交的情況(也可以理解成相交在null上)
                tailA = headB;
            } else {
                tailA = tailA->next;
            }

            if (!tailB) {
                tailB = headA;
            } else {
                tailB = tailB->next;
            }
        }
        return tailA;
    }
};
/* a=tailA, b=tailB

     b7   b8   b9
     a1   a2   a3   a4   a5
     4 -> 1 -> 8 -> 4 -> 5
5 -> 6 -> 1 ---^
b1   b2   b3   b4   b5   b6
a6   a7   a8   a9

在第9步, a9 b9時相遇相交在val=8的node上

*/