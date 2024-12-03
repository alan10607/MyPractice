//BFS O(V) O(V)
class Solution111 {
public:
    int minDepth(TreeNode* root) {
        if (!root) return 0;

        int depth = 0;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) { //本題應該用BFS解而不是DFS, 因為BFS可以最快看到leaf後返回, 而DFS會跑完整張tree
            ++depth;

            for (int i = q.size(); i > 0; --i) {//透過i先確定值, 解決q.size()會變動的問題
                TreeNode* node = q.front(); q.pop();
                if (!node->left && !node->right) return depth; // find leaf
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
        }
        return -1; //不可能
    }
};