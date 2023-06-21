//DFS(BST) O(logV) O(H)
class Solution285 {//lintcode448
public:
    TreeNode* inorderSuccessor(TreeNode* root, TreeNode* p) {
        if(!root) return nullptr;

        if(root->val <= p->val){//root太小, 若剛好為root則會return跳過
            return inorderSuccessor(root->right, p);
        }else{//root太大, 往左找最近
            TreeNode* left = inorderSuccessor(root->left, p);
            return left ? left : root;//若找不到左葉, 此為最左
        }
    }
};