//Backtracking O(V) O(V)
class Solution105 {
public:
    int preIndex = 0;//preorder index
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        int n = preorder.size();
        unordered_map<int, int> inMap;//<val, 位置index>
        for (int i = 0; i < n; ++i) {
            inMap[inorder[i]] = i;//建立map減少重複找查
        }

        return build(0, n - 1, preorder, inMap);
    }

    TreeNode* build(int inStart, int inEnd, vector<int>& preorder, unordered_map<int, int>& inMap) {
        if (inStart > inEnd) return nullptr;

        int val = preorder[preIndex++];
        int inIndex = inMap[val];
        TreeNode* node = new TreeNode(val);
        node->left = build(inStart, inIndex - 1, preorder, inMap);
        node->right = build(inIndex + 1, inEnd, preorder, inMap);
        return node;
    }
};