//DFS(BST) O(V) O(H)
class Solution450 {
public:
    TreeNode* deleteNode(TreeNode* root, int key) {
        if (!root) return root;
        if (key < root->val) {
            root->left = deleteNode(root->left, key);
        } else if (key > root->val) {
            root->right = deleteNode(root->right, key);
        } else { // key == root->val
            if (!root->left) return root->right; // 如果right==null就代表兩者皆null
            if (!root->right) return root->left; // 如果left==null就代表兩者皆null

            // 左右子葉都存在的情況
            TreeNode* new_node = getMin(root->right); // 用右邊最小值取代自己(或用左邊最大值也可以)
            root->right = deleteNode(root->right, new_node->val); // 先刪除這個node的位子, 先連上再刪除可能right tree已經長不一樣
            new_node->left = root->left;
            new_node->right = root->right;
            root = new_node;
        }
        return root;
    }

    TreeNode* getMin(TreeNode* root) {
        while (root->left) {
            root = root->left; // 最左邊就是最小
        }
        return root;
    }
};
/* 刪除node可以分成三種情況:
x表示刪除了

1. 沒有children => 直接刪除就好了
ex: 刪除3
    2               2
1       3   =>  1       x


2. 左右子葉只有一邊存在 => 用那個child取代
ex: 刪除4
    2               2
1       4   =>  1       3
       3               x

or

    2               2
1       4   =>  1       5
         5               x


3. 兩邊子葉都存在 => 用左側的最大值取代 或 用右側最小值取代
ex: 刪除6
    2               2
1       6   =>  1       5
      4   8           4   8
     3 5 7 9         3 x 7 9

or

    2               2
1       6   =>  1       7
      4   8           4   8
     3 5 7 9         3 5 x 9

*/