//DFS(BST) O(V) O(H)
class Solution700 {
public:
    TreeNode* searchBST(TreeNode* root, int val) {
        if (!root) {
            return nullptr;
        } else if (val == root->val) {
            return root;
        } else if (val < root->val) {
            return searchBST(root->left, val);
        } else { // val > root->val
            return searchBST(root->right, val);
        }
    }
};