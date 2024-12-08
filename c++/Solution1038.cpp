//DFS(BST) O(V) O(H)
class Solution1038 {
public:
    int sum = 0;
    TreeNode* bstToGst(TreeNode* root) { // 本題與538完全相同
        if (!root) return nullptr;

        bstToGst(root->right); // inorder反過來從right開始
        sum += root->val;
        root->val = sum;
        bstToGst(root->left);
        return root;
    }
};