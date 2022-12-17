//DFS O(n) O(logn), n = nums.size()
class Solution108 {
public:
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        return build(0, nums.size() - 1, nums);
    }

    TreeNode* build(int start, int end, vector<int>& nums){
        if(start > end) return nullptr;

        int mid = (start + end) / 2;
        TreeNode* root = new TreeNode(nums[mid]);
        root->left = build(start, mid - 1, nums);
        root->right = build(mid + 1, end, nums);
        return root;
    }
};