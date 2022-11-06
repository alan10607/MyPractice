//BFS O(V) O(V)
class Solution102 {
public:
    vector<vector<int>> levelOrder(TreeNode* root) {
        if(!root) return {};

        queue<TreeNode*> q;
        q.push(root);

        vector<vector<int>> res;
        while(!q.empty()){
            vector<int> level;
            for(int i=q.size(); i>0; --i){
                TreeNode *node = q.front(); q.pop();
                level.push_back(node->val);
                if(node->left) q.push(node->left);
                if(node->right) q.push(node->right);
            }
            res.push_back(level);
        }
        return res;
    }
};