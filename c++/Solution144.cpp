//DFS Stack O(V) O(H)
class Solution144 {
public:
    vector<int> preorderTraversal(TreeNode* root) {
        if (!root) return {};
        
        vector<int> res;
        stack<TreeNode*> st;
        st.push(root);
        while (!st.empty()) {
            TreeNode* node = st.top(); st.pop();
            res.push_back(node->val);
            if (node->right) st.push(node->right);
            if (node->left) st.push(node->left);
        }
        return res;
    }
};

//DFS O(V) O(H)
class Solution144_2 {
public:
    vector<int> res;

    vector<int> preorderTraversal(TreeNode* root) {
        traverse(root); // 另一種解法, 本題提示不要用的recursive解法
        return res;
    }

    void traverse(TreeNode* root) {
        if (!root)
            return;

        res.push_back(root->val);
        traverse(root->left);
        traverse(root->right);
    }
};