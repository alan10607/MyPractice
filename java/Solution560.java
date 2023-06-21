package leetCode.java;

import java.util.*;

//O(n) O(n)
class Solution560 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();//<累計總合, 該總和出現次數>
        cnt.put(0, 1);//記得設初始

        int res = 0, sum = 0;
        for(int num : nums){
            sum += num;

            if(cnt.containsKey(sum - k))
                res += cnt.get(sum - k);

            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}