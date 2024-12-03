//DFS(BST) O(V) O(H)
class Solution116 {
public:
    Node* connect(Node* root) {
        if (!root) {
            return nullptr;
        }
        if (root->left) { // 在parent node就直接去修改child的next
            root->left->next = root->right;
        }
        if (root->right) {
            root->right->next = root->next ? root->next->left : nullptr;
        }

        connect(root->left);
        connect(root->right);
        return root;
    }
};


//BFS(BST) O(V) O(H)
class Solution116_2 {
public:
    Node* connect(Node* root) {
        if (!root) return nullptr;

        queue<Node*> q;
        q.push(root);
        while (!q.empty()) {
            for (int i = q.size(); i > 0; --i) {
                Node* node = q.front(); q.pop();
                node->next = i == 1 ? nullptr : q.front(); // 此時q.front()剛好是右邊那個node
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
        }
        return root;
    }
};