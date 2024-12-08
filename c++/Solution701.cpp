//DFS(BST) O(V) O(H)
class Solution701 {
public:
    TreeNode* insertIntoBST(TreeNode* root, int val) {
        if (!root) return new TreeNode(val);
        
        // 不考慮等於情況因為題目保證不重複
        if (val < root->val) {
            root->left = insertIntoBST(root->left, val);
        }
        if (val > root->val) {
            root->right = insertIntoBST(root->right, val);
        }

        return root;
    }
};
/* 依照BST的特性, 比較大小後直接塞入就可以
ex: val=5
        4
    2       7
   1 3


直接塞入:
        4
    2       7
   1 3     5
*/