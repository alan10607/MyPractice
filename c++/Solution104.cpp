//DFS O(V) O(H)
class Solution104 {
public:
    int maxDepth(TreeNode* root) {
        if (!root) return 0;

        int left = maxDepth(root->left);
        int right = maxDepth(root->right);
        return max(left, right) + 1;
    }
};