package leetCode.java;

import java.util.*;

//O(n) O(1)
class Solution448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i=0; i<nums.length; ++i){
            while(nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i=0; i<nums.length; ++i)
            if(i != nums[i] - 1) res.add(i + 1);

        return res;
    }

    public void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}