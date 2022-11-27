//BFS O(V) O(V)
class Solution199 {
public:
    vector<int> rightSideView(TreeNode* root) {
        if(!root) return {};

        vector<int> res;
        queue<TreeNode*> q;
        q.push(root);
        while(!q.empty()){
            int num = -1;
            for(int i=q.size(); i>0; --i){
                TreeNode* node = q.front(); q.pop();
                num = node->val;
                if(node->left) q.push(node->left);
                if(node->right) q.push(node->right);
            }
            res.push_back(num);
        }
        return res;
    }
};