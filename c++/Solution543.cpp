//DFS O(V) O(H)
class Solution543 {
public:
    int res = 0;

    int diameterOfBinaryTree(TreeNode* root) {
        //不是要求高度, 而是任意兩點最遠距離
        dfs(root);
        return res;
    }

    int dfs(TreeNode* root) {
        if(!root) return 0;

        int left = dfs(root->left);
        int right = dfs(root->right);
        res = max(res, left + right);//紀錄最遠距離
        return max(left, right) + 1;
    }
};