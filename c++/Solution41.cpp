//O(n) O(1)
class Solution41 {
public:
    int firstMissingPositive(vector<int>& nums) {
        // must in O(n) time and uses constant extra space
        for (int i = 0; i < nums.size(); ++i) {
            while (nums[i] >= 1 && nums[i] <= nums.size() && nums[i] != nums[nums[i] - 1]) {
                swap(nums[i], nums[nums[i] - 1]);
            }
        }

        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] != i + 1) // nums[i]有可能是INT_MIN, 但題目給的nums.length 不會大於10^5
                return i + 1;
        }
        return nums.size() + 1;
    }
};
/* 若無限制Space Complexity為O(1), 則可直接透過HashSet,
將數字都insert後, 從1開始找第一個null的數字就可,
但如果O(1)怎麼辦呢? 只能依靠該vector本身, 其理念就是透過nums排列來找
1放在index 0的位子, 2放在index 1, nums[i]放在 nums[i]-1,
所以位子不對時, 將nums[i]與nums[nums[i] - 1]換位子, 但保留其他不在>0的數字

想辦法把num放到num-1的位子上
ex1:
3   6   -1  1   3
^i      ^

...經過幾次for loop後
-1  6   3   1   3
^           ^i

找到第一個 i != nums[i]-1
1   6   3   -1  3
    ^

res=2

ex2:
4   3   1   2
^i          ^

2   3   1   4
^i  ^

3   2   1   4
^i      ^

...經過幾次for loop後
1   2   3   4
                ^i

res=5


*/