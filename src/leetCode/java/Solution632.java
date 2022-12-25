package leetCode.java;

import java.util.*;

//Slide Window Intervals O(nm + k) O(nm), n = nums.size(), m = nums[0].size(), k為所有num(約為nm)用在slide window
class Solution632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        List<int[]> all = new ArrayList<>();//<[數字, 哪組], ...>
        for(int i=0; i<nums.size(); i++){
            for(int num : nums.get(i)){
                all.add(new int[]{num, i});
            }
        }
        Collections.sort(all, (a, b) -> a[0] - b[0]);//數字小排到大

        int l = 0, r = 0, check = 0, minLen = Integer.MAX_VALUE;
        int[] cnt = new int[nums.size()];//哪組記數
        int[] res = new int[2];
        while(r < all.size()){
            if(cnt[all.get(r)[1]]++ == 0)
                check++;

            while(check == nums.size()){
                if(minLen > all.get(r)[0] - all.get(l)[0]){
                    minLen = all.get(r)[0] - all.get(l)[0];
                    res[0] = all.get(l)[0];
                    res[1] = all.get(r)[0];
                }

                if(--cnt[all.get(l++)[1]] == 0)
                    check--;
            }

            r++;
        }

        return res;
    }
}