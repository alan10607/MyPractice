//DFS(BST) O(V) O(H)
class Solution98 {
public:
    bool isValidBST(TreeNode* root) { 
        return dfs(root, nullptr, nullptr); 
    }

    bool dfs(TreeNode* root, TreeNode* min_node, TreeNode* max_node) {
        if (!root) return true;
        if (min_node && min_node->val >= root->val) return false; // null時表示無限制
        if (max_node && root->val >= max_node->val) return false;
        return dfs(root->left, min_node, root) && dfs(root->right, root, max_node);
    }
};


//DFS(BST) O(V) O(H)
class Solution98_2 {
public:
    bool isValidBST(TreeNode* root) { 
        return dfs(root, LONG_MIN, LONG_MAX); // 用long否則會有極值問題, root-val=INT_MAX or INT_MIN 會誤判
    }

    bool dfs(TreeNode* root, long min_val, long max_val) {
        if (!root) return true;
        if (min_val >= root->val || root->val >= max_val) return false;
        return dfs(root->left, min_val, root->val) && dfs(root->right, root->val, max_val);
    }
};