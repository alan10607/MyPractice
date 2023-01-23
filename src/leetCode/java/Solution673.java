package leetCode.java;

import java.util.*;

//2D-DP O(n^2) O(n)
class Solution673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n];//到i時可以有的最大子字串
        Arrays.fill(len, 1);
        int[] cnt = new int[n];//到i時可以有的最大子字串的個數
        Arrays.fill(cnt, 1);

        int res = 0, maxLen = 1;
        for(int i=1; i<n; ++i){
            for(int j=0; j<i; ++j){
                if(nums[j] >= nums[i]) continue;

                if(len[j] + 1 == len[i]){
                    cnt[i] += cnt[j];
                }else if(len[j] + 1 > len[i]){
                    len[i] = len[j] + 1;
                    cnt[i] = cnt[j];
                }
            }
            System.out.println(len[i] + "=>" + cnt[i]);
            maxLen = Math.max(maxLen, len[i]);
        }

        for(int i=0; i<n; ++i){
            if(maxLen == len[i]) res += cnt[i];
        }
        return res;
    }
}
/* nums = [2,1,3,4]

    2   1   3   4
len 1   1   2   3
cnt 1   1   2   2

*/