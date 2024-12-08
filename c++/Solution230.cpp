//DFS(BST) O(V) O(H), H為高度
class Solution230 {
public:
    int n = 0, res = -1;
    int kthSmallest(TreeNode* root, int k) {
        traverse(root, k); // 透過recursion方式也可以
        return res;
    }

    void traverse(TreeNode* root, int k){
        if(!root) return;

        traverse(root->left, k);
        if(++n == k){
            res = root->val;
            return;
        }
        traverse(root->right, k);
    }
};


//DFS(BST) O(H + k) O(H), H為高度, 時間複雜度供需要先下H, 再上k
class Solution230_2 {
public:
    int kthSmallest(TreeNode* root, int k) {
        stack<TreeNode*> st;
        while (!st.empty() || root) {
            if (root) {
                st.push(root);
                root = root->left;
            } else {
                root = st.top(); st.pop();
                if (--k == 0) return root->val;
                root = root->right;
            }
        }
        return -1;
    }
};


//DFS(BST) BstNode(): O(V) O(H), V為TreeNode數量, H為高度, kthSmallest(): O(logV) O(H)
class Solution230_3 {
public:
    struct BstNode {
        int val;
        int count; // 含自己與children的node數量
        BstNode* left;
        BstNode* right;
        BstNode(TreeNode* node) : val(0), count(1), left(nullptr), right(nullptr) {
            val = node->val;
            if (node->left) {
                left = new BstNode(node->left);
                count += left->count;
            }
            if (node->right) {
                right = new BstNode(node->right);
                count += right->count;
            }
        }
    };

    int kthSmallest(TreeNode* root, int k) {
        if (!root) return -1;

        BstNode* bstNode = new BstNode(root);
        return find(bstNode, k);
    }

    int find(BstNode* root, int k) {
        if (root->left) {
            int cnt = root->left->count; // 左側的全部數量
            int rank = cnt + 1; // 當前node是第幾大的
            if (k == rank) {
                return root->val;
            } else if (k < rank) {
                return find(root->left, k);
            } else { // k > rank
                return find(root->right, k - rank);
            }
        } else {
            if (k == 1) { // 沒有左側, 第一小就是自己
                return root->val;
            } else {
                return find(root->right, k - 1); // 繼續往右找
            }
        }
    }
};
/*
Follow up 說如果TreeNode很常增刪, 有沒有更好的處理?
可以透過計算左右子樹的數量, 新增另一個Tree來用, 知道兩邊的子樹node數量後就可以用二分法O(logn)找了
*/