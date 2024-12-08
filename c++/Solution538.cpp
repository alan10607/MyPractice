//DFS(BST) O(V) O(H)
class Solution538 {
public:
    int sum = 0;
    TreeNode* convertBST(TreeNode* root) { // 本題與1038完全相同
        if(!root) return nullptr;

        convertBST(root->right); // inorder反過來從right開始
        sum += root->val;
        root->val = sum;
        convertBST(root->left);
        return root;
    }
};