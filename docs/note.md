# Array / Hashing

# Linked List
- https://leetcode.com/problems/merge-two-sorted-lists/
- https://leetcode.com/problems/partition-list/
- https://leetcode.com/problems/merge-k-sorted-lists/description/
- https://leetcode.com/problems/remove-nth-node-from-end-of-list/
- https://leetcode.com/problems/middle-of-the-linked-list/
- https://leetcode.com/problems/linked-list-cycle/
- https://leetcode.com/problems/linked-list-cycle-ii/
- https://leetcode.com/problems/intersection-of-two-linked-lists/
- https://leetcode.com/problems/remove-duplicates-from-sorted-list/


## 鏈表合併/分割/修改
```
ListNode* foo(ListNode* list) {
    ListNode* dummy = new ListNode(-1); // 用來定位起點
    ListNode* tail = dummy;             // 用來指向當前位置

    while (某條件) {
        ...

        tail = tail->next;
    }

    return dummy->next;
}
```

## 快慢指標 -> 查是否有交點
```
bool foo(ListNode* head) {
    ListNode* fast = head;
    ListNode* slow = head;
    while (fast && fast->next) { //條件通常是判斷fast, 且判斷兩層node
        fast = fast->next->next;
        slow = slow->next;
        ...
    }
}
```

# Two Pointers
## Fast-Slow pointers
- https://leetcode.com/problems/remove-duplicates-from-sorted-array/
- https://leetcode.com/problems/remove-element/
- https://leetcode.com/problems/move-zeroes/

### In-Place Modification
```
void fastSlowPointer(vector<int>& nums) {
    int fast = 0, slow = 0;
    while (fast < nums.size()) {
        ...
    }
```


### Sliding Window
- https://leetcode.com/problems/minimum-window-substring/
- https://leetcode.com/problems/permutation-in-string/
- https://leetcode.com/problems/find-all-anagrams-in-a-string/
- https://leetcode.com/problems/longest-substring-without-repeating-characters/
```
string slidingWindow(string s) {
    auto window;
    int l = 0, r = 0;
    while (r < s.length()) { // 以下都是左閉右開[l, r) 方便計算
        window.add(s[r]); // 加入目前這一個
        ++r; // 先移動到下一個

        while (滿足某條件情況下, 開始收縮左邊) {
            // 依照題目要求進行處理
            int len = r - l; // 左閉右開[l, r)

            window.remove(s[l]); // 移除最左邊這個  
            ++l; // 繼續看下一個左邊
        }
    }
}
```


## L-R Pointers
- https://leetcode.com/problems/reverse-string/
- https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

### Binary Search
- https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
- https://leetcode.com/problems/binary-search/

1. 找某目標index, 不存在則返回-1  
    - 搜索區間是[l, r], mid不為target時, 應該尋找[l, mid - 1]或[mid + 1, r]
```
int binarySearch(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            l = mid + 1;
        } else if (nums[mid] > target) {
            r = mid - 1;
        }
    }
    return -1;
}
```

2. lower_bound, 左側邊界, 找第一個不小於某目標的數
    - ex:[0,1,1,1,1], target=1, return=1  
    - 搜索區間是[l, r), mid不為target時, 應該尋找[l, mid)或[mid + 1, r)
    - 若相同, 則應該往左側尋找
```
int lowerBound(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) {
            r = mid
        } else if (nums[mid] < target) {
            l = mid + 1;
        } else if (nums[mid] > target) {
            r = mid;
        }
    }
    return l; // 或r也可以, 因為l == r
}
```

3. upper_bound, 右側邊界, 找第一個大於某目標的數
    - ex:[0,1,1,1,1], target=1, return=5  
    - 搜索區間是[l, r), mid不為target時, 應該尋找[l, mid)或[mid + 1, r)
    - 若相同, 則應該往右側尋找
```
int upperBound(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        if (nums[mid] == target) {
            l = mid + 1
        } else if (nums[mid] < target) {
            l = mid + 1;
        } else if (nums[mid] > target) {
            r = mid;
        }
    }
    return l; // 或r也可以, 因為l == r
}
```


### Palindrome
- https://leetcode.com/problems/longest-palindromic-substring/
```
string palindrome(int l, int r, string s) {
    while (0 <= l && r < s.length() && s[l] == s[r]) {
        --l;
        ++r;
    }
    ++l; // 抵銷最後一次
    --r; // 抵銷最後一次
    return s.substr(l, r - l + 1);
}
```
### N-Sum


# Dynamic Programming
- https://leetcode.com/problems/fibonacci-number/
- https://leetcode.com/problems/coin-change/

DP三要素:
1. 狀態轉移方程式
2. 最優子結構 -> 透過子問題推導出答案
3. 重疊子問題 -> 透過DP table/memo的形式紀錄

1. 自頂向下
```
vector<int> dp(0, ...); // dp table用來當memo紀錄

int solution(所有選擇, 狀態1, 狀態2, ...) {
    for (選擇 : 所有選擇) {
        // 依據選擇要更新狀態的值
        res = 求最值(res, dp(所有選擇, 新狀態1, 新狀態2, ...));
    }
    dp[狀態1] = res;
    return res;
}
```

2. 自底向上
```
int solution(所有選擇, 狀態1, 狀態2, ...) {
    vector<int> dp(0, ...); // 依照題目要求初始化
    dp[0] = ...

    for(int i : 狀態1的所有可能) {
        for(int j : 狀態1的所有可能) {
        for... 
            for (選擇 : 所有選擇) {
                // 依據選擇要更新狀態的值
                dp[i][j][...] = 求最值(dp[i][j][...], 新狀態1, 新狀態2, ...);
            }
        }
    }
}
```
## Knapsack Problem
## Greedy
# Backtracking
- https://leetcode.com/problems/n-queens/
- https://leetcode.com/problems/n-queens-ii/


```
auto res;

void backtracking(所有選擇, 已拜訪, 已選擇) {
    if (完成需要條件) {
        res.push_back(已選擇);
        return;
    }

    for (選擇 : 所有選擇) {
        if (該選擇已被拜訪) continue; 

        // 將該選擇加入已拜訪, 已選擇
        backtracking(nums, visited, selected);
        // 將該選擇移出已拜訪, 已選擇
    }
}
```

## DFS

```
void dfs(Node root) {
    if (!root) return;

    // 進行選擇
    for (Node child : root.children) {
        dfs(child);
    }
    // 撤銷選擇
}
```
## BFS
- https://leetcode.com/problems/minimum-depth-of-binary-tree/
- https://leetcode.com/problems/open-the-lock/description/
```
...
```




## Permutation / Subset / Combination
https://leetcode.com/problems/subsets/
https://leetcode.com/problems/combinations/
- https://leetcode.com/problems/permutations/
https://leetcode.com/problems/subsets-ii/
https://leetcode.com/problems/combination-sum-ii/
https://leetcode.com/problems/permutations-ii/
https://leetcode.com/problems/combination-sum/
https://leetcode.com/problems/combination-sum-iii/
### 依據選項可以分成
1. 不重複, 不可複選
ex: nums=[2,3,6,7], target=7, res=[7]

2. 有重複, 不可複選
ex: nums=[2,3,2,5]

3. 不重複, 可複選
ex: nums=[2,3,6,7], target=7, res=[2,2,3],[7]


### 依據題型可以分成以下樹
1. Subset, Combination(子集, 組合)
```
                        []
        1               2               3
    12      13          23
    123
```

2. Permutation(排列)
```
                        []
        1               2               3
    12      13      21      23      31      32
    123     132     213     231     312     321
```




# Binary Tree
- https://leetcode.com/problems/maximum-depth-of-binary-tree/
- https://leetcode.com/problems/binary-tree-preorder-traversal/
- https://leetcode.com/problems/diameter-of-binary-tree/
- 前序遍歷 (Preorder Traversal): 根 -> 左 -> 右
- 中序遍歷 (Inorder Traversal): 左 -> 根 -> 右
- 後序遍歷 (Postorder Traversal): 左 -> 右 -> 根


## 透過 recursion
```
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
```
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
```
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
```
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
```
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

# Intervals
# Math / Bit

# C++ Basci
sort跟priority_queue剛好相反
## 有關排序
### Sort
#### 小到大 (default)
```
sort(vec.begin(), vec.end());
sort(vec.begin(), vec.end(), less<int>());

auto comp1 = [](int a, int b){return a < b; };
sort(vec.begin(), vec.end(), comp1);
```
#### 大到小
```
sort(vec.begin(), vec.end(), greater<int>());

auto comp2 = [](int a, int b){return a > b; };
sort(vec.begin(), vec.end(), comp2);
```

### Priority Queue
#### 大到小, max heap (default)
```
priority_queue<int, vector<int>> pq;
priority_queue<int, vector<int>, less<int>> pq1;

auto comp1 = [](int a, int b){return a < b; };
priority_queue<int, vector<int>, decltype(comp1)> pq2(comp1);
```
#### 小到大, min heap

```
priority_queue<int, vector<int>, greater<int>> pq3;

auto comp2 = [](int a, int b){return a > b; };
priority_queue<int, vector<int>, decltype(comp2)> pq4(comp2);
```


===

// Need Verify:
二分法小結
Reference:
https://medium.com/@CecileLiu/%E4%BA%8C%E5%88%86%E6%B3%95%E7%9A%84%E5%8D%80%E9%96%93-%E7%B6%B2%E8%B7%AF%E4%B8%8A%E6%9C%80%E8%A9%B3%E7%9B%A1%E7%9A%84%E8%A7%A3%E8%AA%AA-5adf3be3bc5e
https://blog.csdn.net/allenhsu6/article/details/113779530

1. 找某目標, 可以分成左閉右閉[l,r) 或左閉右開[l,r]
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) l = mid + 1;
        else r = mid;
    }
    return -1;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) l = mid + 1;
        else r = mid - 1;
    }
    return -1;
}


2. 找第一個不小於某目標的(lower_bound), ex:[0,1,1,1,1], target=1, return=1
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] < target) l = mid + 1;
        else r = mid;
    }
    return r;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (target <= nums[mid]) r = mid - 1
        else l = mid + 1;
    }
    return l;
}


3. 找第一個大於某目標的(upper_bound), ex:[0,1,1,1,1], target=1, return=5
int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] <= target) l = mid + 1;
        else r = mid;
    }
    return r;
}

int search(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] <= target) l = mid + 1;
        else r = mid - 1;
    }
    return l;
}
