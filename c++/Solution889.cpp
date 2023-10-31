//Backtracking O(V) O(V)
class Solution889 {
public:
    int preIndex = 0, postIndex = 0;
    TreeNode* constructFromPrePost(vector<int>& preorder, vector<int>& postorder) {
        TreeNode* node = new TreeNode(preorder[preIndex++]);

        if (node->val != postorder[postIndex]) {
            node->left = constructFromPrePost(preorder, postorder);
        }

        if (node->val != postorder[postIndex]) {
            node->right = constructFromPrePost(preorder, postorder);
        }

        ++postIndex;
        return node;
    }
};

/* preorder與postorder邏輯相反, 
每當遇到preorder[preIndex] == postorder[postIndex]時, 就代表是是末端node,
此時返回此node, 反之若無相遇, 則依序左到右遍歷

*/
