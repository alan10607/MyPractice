//DFS(BST) O(V) O(H)
class Solution98 {
public:
    bool isValidBST(TreeNode* root) {
        return dfs(root, LONG_MIN, LONG_MAX);//用long否則會有極值問題
    }
    
    bool dfs(TreeNode* root, long minVal, long maxVal){
        if(!root) return true;
        if(root->val <= minVal || maxVal <= root->val) return false;
        return dfs(root->left, minVal, root->val) && dfs(root->right, root->val, maxVal);
    }
};