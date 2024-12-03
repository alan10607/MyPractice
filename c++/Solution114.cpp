//DFS O(V) O(1) 
class Solution114 {
public:
    void flatten(TreeNode* root) { // follow up 要求in-place, O(1) extra space
        if (!root) return;

        flatten(root->left);
        flatten(root->right);

        TreeNode* left = root->left;
        TreeNode* right = root->right;
        root->left = nullptr;
        root->right = left;
        TreeNode* tail = root;
        while (tail->right) { // 找到最後, 再加上right
            tail = tail->right;
        }
        tail->right = right;
    }
};
/*
        1
    2       5
  3   4       6

---------------------

        1
    2       5
      3       6
        4

---------------------

        1
          2
            3
              4
                5
                  6

*/