// DFS O(V) O(V)
class Solution652 {
public:
    vector<TreeNode*> findDuplicateSubtrees(TreeNode* root) {
        unordered_map<string, int> cnt; // <序列化的tree, 出現次數>
        vector<TreeNode*> res;
        traverse(root, cnt, res); // 透過preorder的方式序列化tree
        return res;
    }

    string traverse(TreeNode* root, unordered_map<string, int>& cnt, vector<TreeNode*>& res) {
        if (!root) return "N";

        string tree = to_string(root->val) + "," +
                      traverse(root->left, cnt, res) + "," +
                      traverse(root->right, cnt, res);
        if (cnt[tree] == 1) {
            res.push_back(root);
        }
        ++cnt[tree];
        return tree;
    }
};