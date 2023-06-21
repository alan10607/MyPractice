package leetCode.java;

import java.util.*;

//DP NumArray(): O(n) O(n), sumRange(): O(1) O(n)
class NumArray {//Solution303
    public List<Integer> dp = new ArrayList<>();

    public NumArray(int[] nums) {
        int sum = 0;
        dp.add(sum);
        for(int num : nums){
            sum += num;
            dp.add(sum);
        }
    }

    public int sumRange(int left, int right) {
        return dp.get(right + 1) - dp.get(left);
    }
}
/*
num =   1   2   3   4
sum =   0   1   3   6   10

*/