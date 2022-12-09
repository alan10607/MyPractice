//BFS O(V) O(V)
class Solution103 {
public:
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        if(!root) return {};

        queue<TreeNode*> q;
        q.push(root);
        bool goRight = true;//方向->
        vector<vector<int>> res;
        while(!q.empty()){
            int size = q.size();
            vector<int> level(size);
            for(int i=0; i<size; ++i){
                TreeNode* node = q.front(); q.pop();
                int index = goRight ? i : (size - 1 - i);
                level[index] = node->val;
                if(node->left) q.push(node->left);
                if(node->right) q.push(node->right);
            }
            res.push_back(level);
            goRight = !goRight;
        }
        return res;
    }
};