//O(n) O(1)
class Solution31 {
public:
    void nextPermutation(vector<int>& nums) {
        //must be in place and use only constant extra memory
        int i = nums.size() - 2, j = nums.size() - 1;

        while(i >= 0 && nums[i] >= nums[i + 1]) --i;//等於也略過, 找強制遞減

        if(i >= 0){//若i=-1, 表示為全遞減, 此時為最大, 跳過直接reverse成最小
            while(i < j && nums[i] >= nums[j]) --j;
            swap(nums[i], nums[j]);
        }

        reverse(nums.begin() + i + 1, nums.end());//i+1到end
    }
};
/*
由後往前找某數, 該數之後為降階數列
1   2   7   3   2   1
    i

由後往前找第一個大於該數的數字
1   2   7   3   2   1
    i       j

swap(nums[i], nums[j])
1   3   7   2   2   1
        -   -   -   -

重新sort(剛好是reverse)
1   3   1   2   2   7

*/