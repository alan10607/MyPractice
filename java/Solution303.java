package leetCode.java;

import java.util.*;

//NumArray(): O(n) O(n), sumRange(): O(1) O(n)
class NumArray {//Solution303
    int[] sums;

    public NumArray(int[] nums) {
        this.sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            this.sums[i + 1] = this.sums[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return sums[right + 1] - sums[left];
    }
}
/*
ex: nums=[-2, 0, 3, -5, 2, -1]

nums=       -2  0   3   -5  2   -1
sums=   0   -2  -2  1   -4  -2  -3

sums[0]=0
sums[1]=nums[0]
sums[2]=nums[0]+nums[1]
sums[3]=nums[0]+nums[1]+nums[2]
sums[4]=nums[0]+nums[1]+nums[2]+nums[3]
sums[5]=nums[0]+nums[1]+nums[2]+nums[3]+nums[4]
sums[6]=nums[0]+nums[1]+nums[2]+nums[3]+nums[4]+nums[5]
sums[n]=nums[0]+...+nums[n-1]

假設要求sumRange=[left,right]=[2,5]
res=sums[6]-sums[2]=sums[right+1]-sums[left]

*/