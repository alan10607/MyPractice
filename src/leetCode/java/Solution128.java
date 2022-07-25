package leetCode.java;

import java.util.*;

//Greedy O(n) O(n)
class Solution128 {
    public int longestConsecutive(int[] nums) {
        //must runs in O(n) time
        Set<Integer> memo = new HashSet<>();
        for(int num : nums)
            memo.add(num);

        int res = 0;
        for(int num : nums){
            if(!memo.contains(num - 1)){//只從最小的開始找避免重工
                int next = num + 1;//代表該數字
                int count = 1;//紀錄長度
                while(memo.contains(next)){
                    next++;
                    count++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}