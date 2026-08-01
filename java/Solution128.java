package leetCode.java;

import java.util.*;

//Greedy O(n) O(n)
class Solution128 {
    public int longestConsecutive(int[] nums) {
        //must runs in O(n) time
        Set<Integer> memo = new HashSet<>();
        for (int num : nums) {
            memo.add(num);
        }

        int res = 0;
        for (int num : memo) { // 直接透memo set會更快, 因為nums可能重複大量數字會TLE
            if (!memo.contains(num - 1)) { // 排除非底部選項, 只從最小的開始數
                int cnt = 0;
                while (memo.contains(num + cnt)) {
                    cnt++;
                }
                res = Math.max(res, cnt);
            }
        }
        return res;
    }
}