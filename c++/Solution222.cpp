//DFS O(V) O(H)
class Solution222 {
public:
    int countNodes(TreeNode* root) {
        if (!root) return 0;

        TreeNode* l = root->left;
        TreeNode* r = root->right;
        int left_height = 0, right_height = 0;
        while (l) {
            ++left_height;
            l = l->left;
        }
        while (r) {
            ++right_height;
            r = r->right;
        }

        if (left_height == right_height) {
            return pow(2, left_height + 1) - 1; // 代表此時是Perfect Binary Tree, 全滿
        } else {
            return 1 + countNodes(root->left) + countNodes(root->right);
        }
    }
};


//DFS O(V) O(H)
class Solution222_2 {
public:
    int countNodes(TreeNode* root) { // 沒用到Complete Tree Node特性, 暴力解不太好
        if(!root) return 0; 
        return 1 + countNodes(root->left) + countNodes(root->right);
    }
};