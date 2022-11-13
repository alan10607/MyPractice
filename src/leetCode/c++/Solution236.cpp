//DFS O(V) O(H)
class Solution236 {
public:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        if(!root || root == p || root == q) return root;
        
        //往下找p與q
        TreeNode* l = lowestCommonAncestor(root->left, p, q);
        if(l && l != p && l != q) return l;//已找到父節點, 此時r為null
        
        TreeNode* r = lowestCommonAncestor(root->right, p, q);
        if(r && r != p && r != q) return r;//已找到父節點, 此時l為null

        if(l && r) return root;//找到了

        return l ? l : r;
    }
};