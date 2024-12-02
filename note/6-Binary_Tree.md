# Binary Tree
- https://leetcode.com/problems/maximum-depth-of-binary-tree/
- https://leetcode.com/problems/binary-tree-preorder-traversal/
- https://leetcode.com/problems/diameter-of-binary-tree/
- 前序遍歷 (Preorder Traversal): 根 -> 左 -> 右
- 中序遍歷 (Inorder Traversal): 左 -> 根 -> 右
- 後序遍歷 (Postorder Traversal): 左 -> 右 -> 根


## 透過 recursion
```cpp
vector<int> preorder;
vector<int> inorder;
vector<int> postorder;

void traverse(TreeNode* root) {
    if (!root) return;

    preorder.push_back(root->val); // 前序
    traverse(root->left);
    inorder.push_back(root->val); // 中序
    traverse(root->right);
    postorder.push_back(root->val); // 後序
}
```

## 透過stack traverse
1. 前序
```cpp
vector<int> preorderTraversal(TreeNode* root) {
    vector<int> res;
    stack<TreeNode*> st;
    TreeNode* node = root;
    while (!st.empty() || node) {
        if (node) {
            res.push_back(node->val); // 在進入node就馬上紀錄
            st.push(node);
            node = node->left;
        } else {
            node = st.top(); st.pop();
            node = node->right;
        }
    }

    return res;
}
```

2. 中序
```cpp
vector<int> inorderTraversal(TreeNode* root) {
    vector<int> res;
    stack<TreeNode*> st;
    TreeNode* node = root;
    while (!st.empty() || node) {
        if (node) {
            st.push(node);
            node = node->left;
        } else {
            node = st.top(); st.pop();
            res.push_back(node->val); // 在離開左node後要進入右node前紀錄
            node = node->right;
        }
    }

    return res;
}
```

3. 後序
```cpp
vector<int> postorderTraversal(TreeNode* root) {
    vector<int> res;
    stack<TreeNode*> st;
    TreeNode* node = root;
    TreeNode* last = nullptr; // 後續需要一個last node來代表上一個跳出的node, 避免重新在進入右邊
    while (!st.empty() || node) {
        if (node) {
            st.push(node);
            node = node->left;
        } else {
            TreeNode* p = st.top();
            if (p->right && p->right != last) { // 存在右node且沒訪問過就進入
                node = p->right;
            } else {
                res.push_back(p->val); // 在要離開node時紀錄
                st.pop();
                last = p; // 記錄為前一個node, 代表已經訪問
            }
        }
    }

    return res;
}
```

4. 前中後一起
```cpp
void traverse(TreeNode* root) {
    vector<int> preorder;
    vector<int> inorder;
    vector<int> postorder;
    stack<TreeNode*> st;
    TreeNode* cur = root;
    TreeNode* last = nullptr;

    while (!st.empty() || cur) {
        if (cur) {
            preorder.push_back(cur->val); // 在進入node就馬上紀錄
            st.push(cur);
            cur = cur->left;
        } else {
            TreeNode* p = st.top();
            if (p->right && p->right != last) {
                inorder.push_back(p->val); // 在離開左node後要進入右node前紀錄
                cur = p->right;
            } else {
                if(!p->right) {
                    inorder.push_back(p->val); // 如果沒有右node則直接紀錄
                }
                postorder.push_back(p->val); // 在要離開node時紀錄
                st.pop();
                last = p;
            }
        }
    }

}
```