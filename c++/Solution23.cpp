//LinkedList O(nklogk) O(logk), k = lists.size(), n為鏈表長度, klogk相當於合併排序之時間複雜度
class Solution23 {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if (lists.size() == 0) return nullptr;
        if (lists.size() == 1) return lists[0];
        return split(0, lists.size() - 1, lists);
    }

    ListNode* split(int start, int end, vector<ListNode*>& lists) {
        if (start == end) return lists[start];

        int mid = start + (end - start) / 2;
        ListNode* left = split(start, mid, lists);
        ListNode* right = split(mid + 1, end, lists);

        return merge(left, right);
    }

    ListNode* merge(ListNode* a, ListNode* b) {
        ListNode* dummy = new ListNode(-1);
        ListNode* tail = dummy;
        while (a && b) {
            if (a->val < b->val) {
                tail->next = a;
                a = a->next;
            } else {
                tail->next = b;
                b = b->next;
            }
            tail = tail->next;
        }

        tail->next = a ? a : b;

        return dummy->next;
    }
};


//LinkedList O(nklogk) O(logk), k = lists.size(), pq.push或pop需要log(k), 總共有nk個node
class Solution23_2 {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        auto comp = [](ListNode* a, ListNode* b) { return a->val > b->val; }; // min heap
        priority_queue<ListNode*, vector<ListNode*>, decltype(comp)> pq(comp);

        for (ListNode* node : lists) {
            if (node) {
                pq.push(node);
            }
        }

        ListNode* dummy = new ListNode(-1);
        ListNode* tail = dummy;
        while (!pq.empty()) {
            ListNode* node = pq.top(); pq.pop();
            tail->next = node;
            tail = tail->next;

            if (node->next) { // 後面還有就繼續塞入
                pq.push(node->next);
            }
        }
        return dummy->next;
    }
};