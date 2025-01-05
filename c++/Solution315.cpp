//Binary Search O(nlogn) O(n)
class Solution315 {
public:
    vector<int> countSmaller(vector<int>& nums) {
        vector<int> sorted_nums, res(nums.size());
        for (int i = nums.size() - 1; i >= 0; --i) { // 從最後面開始加到sorted_nums, 這樣前面的res才能看到比他小的有幾個
            auto index = lower_bound(sorted_nums.begin(), sorted_nums.end(), nums[i]);
            int gap = index - sorted_nums.begin(); // 計算前面有幾個
            res[i] = gap;
            sorted_nums.insert(sorted_nums.begin() + gap, nums[i]);
        }
        return res;
    }
};


//DFS O(nlogn) O(n)
class Solution315_2 { // 此方法比較慢, 有機會TLE
public:
    struct TreeNode {
        int val;
        int smaller_cnt;
        TreeNode* left;
        TreeNode* right;
        TreeNode(int x, int y) : val(x), smaller_cnt(y), left(nullptr), right(nullptr) {}
    };

    vector<int> countSmaller(vector<int>& nums) {
        vector<int> res(nums.size());
        TreeNode* root = nullptr;
        for (int i = nums.size() - 1; i >= 0; --i) {
            res[i] = insertNode(root, nums[i]);
        }
        return res;
    }

    int insertNode(TreeNode*& root, int num) { // 引用指標*&
        if (!root) {
            root = new TreeNode(num, 0);
            return 0;
        }

        if (root->val > num) { // 往左側加入
            ++root->smaller_cnt;
            return insertNode(root->left, num);
        } else if (root->val == num) { // 往右側加入
            return root->smaller_cnt + insertNode(root->right, num);
        } else { // root->val < num, 往右側加入
            return root->smaller_cnt + 1 + insertNode(root->right, num); // 包含root val 再+1
        }
    }
};