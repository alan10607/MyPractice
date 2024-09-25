//O(n) O(1)
class Solution31 {
public:
    void nextPermutation(vector<int>& nums) {
        // must be in place and use only constant extra memory
        int i = nums.size() - 2, j = nums.size() - 1;

        while (i >= 0 && nums[i] >= nums[i + 1]) { // 等於也略過, 找強制遞減
            --i;
        }

        if (i >= 0) { // 若i=-1, 表示為全遞減, 此時為最大, 跳過直接reverse成最小
            while (j > i && nums[j] <= nums[i]) {
                --j;
            }
            swap(nums[i], nums[j]);
        }

        reverse(nums.begin() + i + 1, nums.end());
    }
};
/*
順序:
1234
1243
1324
1342
4123
4231
4321

ex 1:
由後往前找某數, 該數的後面比自己大
1   2   7   3   2   1
    i

由後往前找第一個大於該數的數字
1   2   7   3   2   1
    i       j

swap(nums[i], nums[j])
1   3   7   2   2   1
        -   -   -   -

重新sort i 之後的位置, 由於是遞減數列所以可以剛好用reverse
1   3   1   2   2   7


ex 2 重複數字的情況:
由後往前找某數, 該數的後面比自己大
1   2   3   3
    i

由後往前找第一個大於該數的數字
1   2   3   3
    i       j

swap(nums[i], nums[j])
1   3   3   2
        _   _

reverse i 之後的位置
1   3   2   3

*/