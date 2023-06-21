//DFS O(V) O(H)
class Solution1448 {
public:
    int goodNodes(TreeNode* root) {
        return dfs(root, INT_MIN);
    }

    int dfs(TreeNode* root, int last) {
        if(!root) return 0;

        int cnt = 0;
        if(root->val >= last){//等於也算
            last = root->val;
            ++cnt;
        }
        cnt += dfs(root->left, last);
        cnt += dfs(root->right, last);
        return cnt;
    }
};