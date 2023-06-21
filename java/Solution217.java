package leetCode.java;

import java.util.*;

//O(n) O(1)
class Solution217 {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> memo = new HashSet<>();
        for(int num : nums){
            if(memo.contains(num)){
                return true;
            }else{
                memo.add(num);
            }
        }
        return false;
    }
}