# Binary Tree
- https://leetcode.com/problems/maximum-depth-of-binary-tree/
- https://leetcode.com/problems/binary-tree-preorder-traversal/
- https://leetcode.com/problems/diameter-of-binary-tree/
- https://leetcode.com/problems/invert-binary-tree/
- https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
- https://leetcode.com/problems/flatten-binary-tree-to-linked-list
- https://leetcode.com/problems/maximum-binary-tree/


## 解題思路
如何解題? 通常有兩種solution:

1. 遍歷: 是否可以通過一次遍歷binary tree解決? 可以的話建立traverse函式與外部變量解決
```cpp
void traverse(TreeNode* root) {
    if (!root) return nullptr;

    ...
    traverse(root->left); // 透過遍歷思維
    traverse(root->right);
}
```

2. 分解問題: 是否可以分解成子問題, 透過子問題推導出答案? 可以的話利用子問題的return值來解決問題
```cpp
TreeNode* subSolution(TreeNode* root) {
    if(!root) return nullptr;
    
    TreeNode* left = subSolution(root->left);
    TreeNode* right = subSolution(root->right);
    ...
    return root;
}
```

無論使用哪種思維模式, 都需要思考：
如果單獨抽出一個二元樹節點, 它需要做什麼事情? 需要在什麼時候(前/中/後序)做?
其他的node, 遞迴函式會幫你在所有節點上執行相同的操作


## Traverse
- 前序遍歷 (Preorder Traversal): 根 -> 左 -> 右
- 中序遍歷 (Inorder Traversal): 左 -> 根 -> 右
- 後序遍歷 (Postorder Traversal): 左 -> 右 -> 根


### 透過 recursion
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

### 透過while + stack
1. 前序
```cpp
vector<int> preorderTraversal(TreeNode* node) {
    vector<int> res;
    stack<TreeNode*> st;
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
vector<int> inorderTraversal(TreeNode* node) {
    vector<int> res;
    stack<TreeNode*> st;
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
vector<int> postorderTraversal(TreeNode* node) {
    vector<int> res;
    stack<TreeNode*> st;
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


## 關於序列化
- https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
- https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
- https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
- https://leetcode.com/problems/find-duplicate-subtrees/
- https://leetcode.com/problems/serialize-and-deserialize-binary-tree/


1. 序列化後內容不含null:
    - 只有獲得兩種order才有辦法還原tree
    1. preorder + inorder 或 postorder + inorder -> 可還原成唯一 binary tree
    2. preorder + posorder -> 有多個可能
```cpp
ex: preorder = [1,2,3], postorder = [3,2,1], 可以長得是
    1         1
  2     or      2
3                 3
```
2. 序列化後內容包含null:
    1. preorder 或 postorder -> 可還原成唯一 binary tree
    2. inorder -> 有多個可能
```cpp
ex: inorder = [N,1,N,1,N]可以長得是
    1          1
  1   N  or  N   1
N   N          N   N
```

## Binary Search Tree
- https://leetcode.com/problems/kth-smallest-element-in-a-bst/
- https://leetcode.com/problems/convert-bst-to-greater-tree/
- https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
- https://leetcode.com/problems/validate-binary-search-tree/
- https://leetcode.com/problems/search-in-a-binary-search-tree
- https://leetcode.com/problems/insert-into-a-binary-search-tree/
- https://leetcode.com/problems/delete-node-in-a-bst/


BST, 對於某個node來, 左子樹與其底下node都小於自身, 右子樹與其底下node都大於自身  
ex:
```cpp
是BST:
        7
    4       9
  1   5       10

不是BST
        7
    4       9
  1   8       10
```

## BFS
- https://leetcode.com/problems/minimum-depth-of-binary-tree/
```cpp
void bfs(TreeNode* root) {
    if (!root) return;

    queue<TreeNode*> q;
    q.push(root);
    int depth = 1; // 當前的層數
    while (!q.empty()) {
        int sz = q.size();
        for (int i = q.size(); i > 0; --i) { // 透過i先確定值, 解決q.size()會變動的問題
            TreeNode* node = q.front(); q.pop();
            if (node->left) q.push(node->left);
            if (node->right) q.push(node->right);
        }
        depth++;
    }
}
```