//DFS O(V^2) O(H)
class Solution113 {
public:
    vector<vector<int>> res;

    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        vector<int> nums;
        dfs(nums, targetSum, root);
        return res;
    }

    void dfs(vector<int>& nums, int target, TreeNode* root){
        if(!root) return;//root->val可能是負的

        nums.push_back(root->val);
        target -= root->val;
        if(target == 0 && !root->left && !root->right)
            res.push_back(nums);//return root-to-leaf path only, dfs後續會自動return, 並會pop_back


        dfs(nums, target, root->left);
        dfs(nums, target, root->right);

        nums.pop_back();//記得要移除
    }
};