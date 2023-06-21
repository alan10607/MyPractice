//DFS O(V) O(H)
class Solution110 {
public:
    bool isBalanced(TreeNode* root) {
        return dfs(root) != -1;
    }

    int dfs(TreeNode* root){
        if(!root) return 0;

        int left_height = dfs(root->left);
        int right_height = dfs(root->right);
        //node differ in height by no more than 1.
        if(left_height == -1 || right_height == -1 || abs(left_height - right_height) > 1)
            return -1;

        return max(left_height, right_height) + 1;
    }
};