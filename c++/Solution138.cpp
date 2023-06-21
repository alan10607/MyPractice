//LinkedList O(n) O(n)
class Solution138 {
public:
    Node* copyRandomList(Node* head) {
        unordered_map<Node*, Node*> oldToNew;
        Node* cur = head;
        while(cur){
            oldToNew[cur] = new Node(cur->val);
            cur = cur->next;
        }

        cur = head;
        while(cur){
            oldToNew[cur]->next = oldToNew[cur->next];
            oldToNew[cur]->random = oldToNew[cur->random];//unordered_map可以接受查詢null
            cur = cur->next;
        }
        return oldToNew[head];
    }
};