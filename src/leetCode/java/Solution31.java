package leetCode.java;

//O(n) O(1)
class Solution31 {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2, j = nums.length - 1;
        while(i >= 0 && nums[i] >= nums[i + 1]) --i;

        if(i >= 0){
            while(i < j && nums[i] >= nums[j]) --j;
            swap(i, j, nums);
        }

        reverse(i + 1, nums.length - 1, nums);
    }

    public void swap(int a, int b, int[] nums){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public void reverse(int l, int r, int[] nums){
        while(l < r){
            swap(l++, r--, nums);
        }
    }
}
/*
2   7   3   1
        i   j

2   7   3   1
i           j

3   7   2   1
i           j

3   7   2   1
i           j

3   1   2   7

*/