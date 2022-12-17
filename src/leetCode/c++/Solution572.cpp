//DFS O(VU) O(H), U = root節點數, V = subRoot節點數, H = subRoot高度
class Solution572 {
public:
    bool isSubtree(TreeNode* root, TreeNode* subRoot) {
        if(!subRoot) return true;
        if(!root) return false;
        if(!isSameTree(root, subRoot))
            return isSubtree(root->left, subRoot) || isSubtree(root->right, subRoot);

        return true;
    }

    bool isSameTree(TreeNode* a, TreeNode* b) {
        if(!a || !b) return !a && !b;
        if(a->val != b->val) return false;
        return isSameTree(a->left, b->left) && isSameTree(a->right, b->right);
    }
};