# Binary Tree
- https://leetcode.com/problems/maximum-depth-of-binary-tree/
- https://leetcode.com/problems/binary-tree-preorder-traversal/
- https://leetcode.com/problems/diameter-of-binary-tree/
- https://leetcode.com/problems/balanced-binary-tree/
- https://leetcode.com/problems/same-tree/
- https://leetcode.com/problems/count-good-nodes-in-binary-tree/
- https://leetcode.com/problems/invert-binary-tree/
- https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
- https://leetcode.com/problems/flatten-binary-tree-to-linked-list
- https://leetcode.com/problems/maximum-binary-tree/
- https://leetcode.com/problems/count-complete-tree-nodes/


## 各種Tree
### 有序
1. Binary Search Tree
```cpp
            4
    2               6   
1       3       5       7
```

### 無序
1. Complete Binary Tree: 除了最後一層都是滿的, 如BST順序
```cpp
            x
    x               x
x       x       x
```

2. Full Binary Tree: 每個node都有兩個children
```cpp
            x
    x               x
x       x
       x x
```

3. Perfect Binary Tree: 全滿, node數量為 2^高度-1
```cpp
            x
    x               x
x       x       x       x

```

4. Height Balanced Binary Tree: 每一個node, 它的left, right高度差都不能>1
```cpp
            x
    x               x
x       x

```



## 解題思路
如何解題? 通常有兩種solution:

1. 遍歷: 是否可以通過一次遍歷binary tree解決? 可以的話建立traverse函式與外部變量解決
```cpp
void traverse(TreeNode* root) {
    if (!root) return;

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

無論使用哪種思維模式, 都需要思考:
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


## Tree 序列化
- https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
- https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
- https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
- https://leetcode.com/problems/find-duplicate-subtrees/
- https://leetcode.com/problems/serialize-and-deserialize-binary-tree/


### 序列化後內容不含null (直接跳過null node)
- 不包含 null 時, 單獨一種 traversal 無法唯一還原tree
- 需要兩種traversal, 且其中必須包含inorder

1. preorder + inorder 或 postorder + inorder -> 可還原成唯一 binary tree
這類型解的法都是透過pre or post是固定順序, 然後可以透過recursion的方式依序把inorder分段處理

- A. preorder + inorder
```cpp
preorder  前序 [root][...left...][...right...]
inorder   中序 [...left...][root][...right...]

pre_index   v
preorder =  3   9   20   15   7

           [l]  v   [    r     ]
inorder  =  9   3   15   20   7

依序依照preorder index移動的順序 (依序建立 left, right) 進入recursion, 並利用inorder切割左右subtree
```
```cpp
unordered_map<int, int> in_map; //<val, 位置index>, 建立map減少重複找查
int pre_index = 0; // preorder index

TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
    for (int i = 0; i < inorder.size(); ++i) {
        in_map[inorder[i]] = i;
    }

    return build(preorder, inorder, 0, inorder.size() - 1);
}

TreeNode* dfs(vector<int>& preorder, vector<int>& inorder, int in_start, int in_end) {
    if (in_start > in_end) return nullptr;

    int val = preorder[pre_index++]; // pre_index剛好是dfs順序, 記得+1
    TreeNode* node = new TreeNode(val);
    int in_index = in_map[val];
    node->left = build(preorder, inorder, in_start, in_index - 1); // 左邊先開始
    node->right = build(preorder, inorder, in_index + 1, in_end);
    return node;
}
```


- B. postorder + inorder
```cpp
postorder 後序 [...left...][...right...][root]
inorder   中序 [...left...][root][...right...]


post_index                    v
postorder = 9   15  7    20   3

           [l]  v   [    r     ]
inorder  =  9   3   15   20   7

依序依照preorder index移動的順序 (依序建立 right, left) 進入recursion, 並利用inorder切割左右subtree
```
```cpp
unordered_map<int, int> in_map; //<val, 位置index>, 建立map減少重複找查
int post_index; // postorder index
TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
    post_index = postorder.size() - 1;
    for (int i = 0; i < inorder.size(); ++i) {
        in_map[inorder[i]] = i;
    }
    return build(inorder, postorder, 0, inorder.size() - 1);
}

TreeNode* build(vector<int>& inorder, vector<int>& postorder, int in_start, int in_end) {
    if (in_start > in_end) return nullptr;

    int val = postorder[post_index--]; // postorder, 從最後開始往前退
    TreeNode* node = new TreeNode(val);
    int in_index = in_map[val];
    node->right = build(inorder, postorder, in_index + 1, in_end); // 改成先從right進去以符合postorder順序
    node->left = build(inorder, postorder, in_start, in_index - 1);
    return node;
}
```

2. preorder + posorder -> 有多個可能, 沒有inorder無法確定
```cpp
ex: preorder = [1,2,3], postorder = [3,2,1], 可以長得是
    1         1                    1
  2     or      2       or       2
3                 3                3
```


### 序列化後內容包含null:
1. preorder 或 postorder -> 可還原成唯一 binary tree
ex: preorder
```cpp
    1
2       3
       4
Serialize: 依照preorder順序 backtracking得到: "1,2,N,N,3,4,N,N,N"
Deserialize: 透過preorder順序 backtracking還原
```
```cpp
string serialize(TreeNode* root) {
    // Pre-order 前序遍歷: root, left, right
    if (root == nullptr) {
        return "N";
    }
    return to_string(root->val) + "," + serialize(root->left) + ","  + serialize(root->right);
}

TreeNode* deserialize(string data) {
    stringstream ss(data);
    queue<string> q;
    string val;
    while (ss >> val) {
        q.push(val);
    }
    return dfs(q);
}

TreeNode* dfs(queue<string>& q) {
    string val = q.front(); q.pop();
    if (val == "N") {
        return nullptr;
    }

    TreeNode* node = new TreeNode(stoi(val));
    node->left = dfs(q);
    node->right = dfs(q);
    return node;
}
```

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
- https://leetcode.com/problems/unique-binary-search-trees/
- https://leetcode.com/problems/unique-binary-search-trees-ii/
- https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
- https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
- https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/


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
- https://leetcode.com/problems/binary-tree-level-order-traversal/
- https://leetcode.com/problems/binary-tree-right-side-view/
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

## Merge Sort
Merge Sort就是 Binary Tree 的後序遍歷
- https://leetcode.com/problems/sort-an-array/
- https://leetcode.com/problems/reverse-pairs/

```cpp
mergeSort(nums, 0, nums.size() - 1);

void mergeSort(vector<int>& nums, int start, int end) {
    if (start >= end) return;

    int mid = (end - start) / 2 + start;
    mergeSort(nums, start, mid); // 拆成 [start, mid] and [mid + 1, end]
    mergeSort(nums, mid + 1, end);
    merge(nums, start, mid, mid + 1, end);
}

void merge(vector<int>& nums, int start1, int end1, int start2, int end2) {
    vector<int> tmp(end2 - start1 + 1); // 用來放置sorted的資料
    int i = 0, j = start1, k = start2;
    while (j <= end1 && k <= end2) { // 把較小的先複製到tmp
        if (nums[j] < nums[k]) {
            tmp[i++] = nums[j++];
        } else { // nums[j] >= nums[k]
            tmp[i++] = nums[k++];
        }
    }

    while (j <= end1) { // 補下剩下的
        tmp[i++] = nums[j++];
    }

    while (k <= end2) {
        tmp[i++] = nums[k++];
    }

    for (int idx = 0; idx < tmp.size(); ++idx) { // 將tmp複製回原本array
        nums[start1 + idx] = tmp[idx];
    }
}
```

## Quick Sort
- https://leetcode.com/problems/kth-largest-element-in-an-array/
```cpp
quickSort(nums, 0, nums.size() - 1);

void quickSort(vector<int>& nums, int start, int end) {
    if (start >= end) return;

    int p = start + (rand() % (end + 1 - start)); // [start, end]範圍的隨機數, 避免已排序數列之類的極端情況
    int pivot = nums[p];
    swap(nums[start], nums[p]); // 先放到左邊待命, 結束while再換回來

    int l = start + 1;
    int r = end;
    while (l <= r) {// r = mid - 1的版本, 要是<=
        // 二路快排, nums[l]==pivot 或 nums[r]==pivot時不跳過, 直接交換,
        // l,r同時往中間移動, 讓大量相同數字可以分散到左右兩區, 避免左右partition不平衡使Quick Sort退化
        if (nums[l] < pivot) {
            ++l;
        } else if (nums[r] > pivot) {
            --r;
        } else { // nums[l] >= pivot && pivot >= nums[r]
            swap(nums[l++], nums[r--]);
        }
    }

    // l>r時, [start ... r][l ... end], r正好是在左半部分的最後一個位置, 
    // 跟l換的話會把nums[l](應該放在右側的)換到左側去了
    swap(nums[start], nums[r]);

    quickSort(nums, start, r - 1);
    quickSort(nums, r + 1, end);
}
```

### Quick Select
- https://leetcode.com/problems/kth-largest-element-in-an-array/
```cpp
quickSelect(nums, 0, nums.size() - 1, index);
    
int quickSelect(vector<int>& nums, int start, int end, int index) {
    int pivot = nums[start]; // 直接用最左當pivot的版本
    //or int pivot = start + (rand() % (end + 1 - start)); 避免極端情況
    int l = start + 1;
    int r = end;
    while (l <= r) {
        // 二路快排, nums[l]==pivot 或 nums[r]==pivot時不跳過, 直接交換,
        // l,r同時往中間移動, 讓大量相同數字可以分散到左右兩區, 避免左右partition不平衡使Quick Sort退化
        if (nums[l] > pivot) {
            ++l;
        } else if (nums[r] < pivot){
            --r;
        } else {
            swap(nums[l++], nums[r--]);
        }
    }

    // l>r時, [start ... r][l ... end], r正好是在左半部分的最後一個位置, 
    // 跟l換的話會把nums[l](應該放在右側的)換到左側去了
    swap(nums[start], nums[r]);

    if (index == r) {
        return nums[r];
    } else if (index < r) {
        return quickSelect(nums, start, r - 1, index);
    } else { // index > l
        return quickSelect(nums, r + 1, end, index);
    }
}
```

## Trie
- https://leetcode.com/problems/implement-trie-prefix-tree/
- https://leetcode.com/problems/replace-words/
- https://leetcode.com/problems/word-search-ii/
- https://leetcode.com/problems/design-add-and-search-words-data-structure/
```cpp
從root開始每條有26個分支, 分別印為下一個字母
ex: 存入apple, app, ape, bat

Trie會長這樣:

root
├── a
│   └── p
│       ├── p ✓
│       │   └── l
│       │       └── e ✓
│       └── e ✓
│
└── b
    └── a
        └── t ✓

每個Trie會有:
class Trie {
public:
    vector<Trie*> children; // 用來記錄下一個字母是否存在, null表示不存在
    bool end_flag; // 是否是單字結尾
    
    Trie() {
        children = vector<Trie*>(26, nullptr);
        end_flag = false;
    }
    ...

}
```

TODO
648. Replace Words
68.1%
Med.

677. Map Sum Pairs
56.9%
Med.

208. Implement Trie (Prefix Tree)
67.1%
Med.

211. Design Add and Search Words Data Structure
46.5%
Med.

1804. Implement Trie II (Prefix Tree)
63.0%
Med.

139. Word Break
47.7%
Med.

140. Word Break II
52.6%
Hard

14. Longest Common Prefix
44.6%
Easy

386. Lexicographical Numbers
72.9%
Med.

692. Top K Frequent Words
58.8%
Med.

