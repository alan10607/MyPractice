//DFS O(V) O(H)
class Solution101 {
public:
    bool isSymmetric(TreeNode* root) {
        if(!root) return true;
        return check(root->left, root->right);
    }

    bool check(TreeNode* a, TreeNode* b){
        if(!a || !b) return !a && !b;
        if(a->val != b->val) return false;
        return check(a->left, b->right) && check(a->right, b->left);
    }
};