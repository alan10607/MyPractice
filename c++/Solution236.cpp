//DFS O(V) O(H)
class Solution236 {
public:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (!root) return nullptr;
        if (root == p || root == q) return root; // 找到了p或q了

        TreeNode* left = lowestCommonAncestor(root->left, p, q);
        TreeNode* right = lowestCommonAncestor(root->right, p, q);
        if (left && right) return root; // 找到LCA node了
        return left ? left : right; // 將有找的node往上傳
    }
};
/* 三種情況
1. p, q分別在左右子葉中 -> return root
    root
p          q

2. p, q只在左子葉或右子葉中 -> return 存在的那個子葉
    root                    root
p,q             or                 p,q

3. p, q不存在root的子葉中 -> return null
    root

*/


class Solution236_2 {
public:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) { // 優化版本
        if (!root || root == p || root == q) return root;

        TreeNode* left = lowestCommonAncestor(root->left, p, q);
        if (left && left != p && left != q) return left; // 如果left不為p or q, 可以提早回傳left
        TreeNode* right = lowestCommonAncestor(root->right, p, q);
        if (left && right) return root;
        return left ? left : right;
    }
};