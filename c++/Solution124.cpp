//DFS O(V) O(H)
class Solution124 {
public:
    int res = INT_MIN;

    int maxPathSum(TreeNode* root) {
        dfs(root);
        return res;
    }

    int dfs(TreeNode* root){
        if(!root) return 0;

        int left = max(0, dfs(root->left));
        int right = max(0, dfs(root->right));
        res = max(res, root->val + left + right);
        return root->val + max(left, right);
    }
};