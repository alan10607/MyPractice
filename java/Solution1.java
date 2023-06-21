package leetCode.java;

import java.util.*;

//O(n) O(n)
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> memo = new HashMap<>();//<數字, 位置>
        for(int i=0; i<nums.length; i++){
            if(memo.containsKey(target - nums[i])){
                return new int[]{memo.get(target - nums[i]), i};
            }else{
                memo.put(nums[i], i);
            }
        }
        return new int[]{};
    }
}