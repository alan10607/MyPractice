//Backtracking O(V) O(V)
class Solution106 {
public:
    int postIndex;
    TreeNode* buildTree(vector<int>& inorder, vector<int>& postorder) {
        int n = inorder.size();
        postIndex = n - 1;
        unordered_map<int, int> inMap;//<val, index>
        for (int i = 0; i < n; ++i) {
            inMap[inorder[i]] = i;
        }

        return build(0, n - 1, inMap, postorder);
    }

    TreeNode* build(int inStart, int inEnd, unordered_map<int, int>& inMap, vector<int>& postorder) {
        if (inStart > inEnd) return nullptr;

        int val = postorder[postIndex--];
        int inIndex = inMap[val];
        TreeNode* node = new TreeNode(val);
        node->right = build(inIndex + 1, inEnd, inMap, postorder);
        node->left = build(inStart, inIndex - 1, inMap, postorder);
        return node;
    }
};