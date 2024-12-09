//DFS(BST) Catalan Number O((4^n)/(n^(1/2))) O((4^n)/(n^(1/2)))
class Solution95 {
public:
    vector<TreeNode*> generateTrees(int n) {
        return build(1, n); // 96題的進階, 直接窮舉全部
    }

    vector<TreeNode*> build(int start, int end) {
        if (start > end) return {nullptr}; // 不存在, 直接給一個null in vector

        vector<TreeNode*> res;
        for (int i = start; i <= end; ++i) {
            vector<TreeNode*> lefts = build(start, i - 1);
            vector<TreeNode*> rights = build(i + 1, end);
            for (TreeNode* left : lefts) { // 直接窮舉所有可能
                for (TreeNode* right : rights) {
                    TreeNode* root = new TreeNode(i);
                    root->left = left;
                    root->right = right;
                    res.push_back(root);
                }
            }
        }
        return res;
    }
};