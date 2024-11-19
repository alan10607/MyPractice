//NumArray(): O(n) O(n), sumRange(): O(1) O(n)
class NumArray {//Solution303
public:
    vector<int> sums;
    NumArray(vector<int>& nums) {
        sums.resize(nums.size() + 1, 0); // 需要resize, 長度為nums.size() + 1
        for (int i = 0; i < nums.size(); ++i) {
            sums[i + 1] = nums[i] + sums[i];
        }
    }

    int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }
};
/*
index   0     1     2     3
nums=   1     2     3     4
           \     \     \     \
sums=   0 <-- 1 <-- 3 <-- 6 <-- 10

*/