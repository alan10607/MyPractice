package leetCode.java;

import java.util.*;

//O(n) O(n)
class Solution525 {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> level = new HashMap<>();//<sum, 位置>
        level.put(0, -1);

        int sum = 0, res = 0;
        for(int i=0; i<nums.length; i++){
            sum += nums[i] == 1 ? 1 : -1;
            if(level.containsKey(sum)){
                res = Math.max(res, i - level.get(sum));
            }else{
                level.put(sum, i);
            }
        }
        return res;
    }
}