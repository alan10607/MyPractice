//DFS O(n^2) O(n), n = nums.size()
class Solution654 {
public:
    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        return buildTree(nums, 0, nums.size() - 1);
    }

    TreeNode* buildTree(vector<int>& nums, int start, int end) {
        if (start > end) return nullptr;

        // 線性找出最大的數
        int index = -1, max_num = INT_MIN;
        for (int i = start; i <= end; ++i) {
            if (nums[i] > max_num) {
                index = i;
                max_num = nums[i];
            }
        }

        // build node
        TreeNode* root = new TreeNode(max_num);
        root->left = buildTree(nums, start, index - 1);
        root->right = buildTree(nums, index + 1, end);
        return root;
    }
};


//DFS O(n) O(n), n = nums.size()
class Solution654_2 {
public:
    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        deque<TreeNode*> dq; // 保持val遞減

        for (int num : nums) {
            TreeNode* cur = new TreeNode(num);
            while (!dq.empty() && num > dq.back()->val) {
                cur->left = dq.back();
                dq.pop_back();
            }

            if (!dq.empty()) {
                dq.back()->right = cur;
            }

            dq.push_back(cur);
        }
        return dq.front();
    }
};
/*
nums = [3,2,1,6,0,5]

3
st=[3]

3
 2
st=[3,2]

3
 2
  1
st=[3,2,1]

遇到6, 進入while loop
3
 2 6
  1
st=[3,2]

3 6
 2
  1
st=[3]

 6
3
 2
  1
st=[6]

離開while
  6
3   0
 2
  1
st=[6,0]

遇到5, 進入while loop
  6  5
3   0
 2
  1
st=[6]


離開while, 進入if
   6
3     5
 2   0
  1
st=[6]
*/