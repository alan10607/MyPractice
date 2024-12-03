//DFS O(V) O(H), H為tree深度, 最多為V
class Solution226 {
public:
    TreeNode* invertTree(TreeNode* root) {
        if (!root) return nullptr;

        TreeNode* tmp = root->left;
        root->left = root->right;
        root->right = tmp;
        invertTree(root->left); // 透過遍歷思維
        invertTree(root->right);
        return root;
    }
};


//DFS O(V) O(H), H為tree深度, 最多為V
class Solution226_2 {
    TreeNode* invertTree(TreeNode* root) {
        if(!root) return nullptr;
        
        TreeNode* left = invertTree(root->left); // 另一個解法, 透過分解思路
        TreeNode* right = invertTree(root->right);
        root->left = right;
        root->right = left;
        return root;
    }
};