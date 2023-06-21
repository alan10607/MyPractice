//DFS O(V) O(H)
class Solution863 {
public:
    vector<int> res;

    vector<int> distanceK(TreeNode* root, TreeNode* target, int k) {
        unordered_map<TreeNode*, TreeNode*> parent;
        unordered_set<TreeNode*> visited;
        findParent(root, parent);
        dfs(target, parent, k, visited);
        return res;
    }

    void findParent(TreeNode* root, unordered_map<TreeNode*, TreeNode*>& parent){
        if(!root) return;
        if(root->left) parent[root->left] = root;
        if(root->right) parent[root->right] = root;
        findParent(root->left, parent);
        findParent(root->right, parent);
    }

    void dfs(TreeNode* target, unordered_map<TreeNode*, TreeNode*>& parent, int k, unordered_set<TreeNode*>& visited){
        if(!target || visited.count(target)) return;
        visited.insert(target);

        if(k == 0){
            res.push_back(target->val);
            return;
        }

        if(parent.count(target))//向上找
            dfs(parent[target], parent, k - 1, visited);

        dfs(target->left, parent, k - 1, visited);
        dfs(target->right, parent, k - 1, visited);
    }
};