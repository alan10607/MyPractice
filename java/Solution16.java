package leetCode.java;

import java.util.*;

//LR Pointer O(n^2) O(logn), 空間複雜度為排序所需
class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for(int i=0; i<nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1])
                continue;

            int l = i + 1;
            int r = nums.length - 1;
            while(l < r){
                if(l > i + 1 && nums[l] == nums[l - 1]){
                    l++;
                    continue;
                }
                if(r < nums.length - 1 && nums[r] == nums[r + 1]){
                    r--;
                    continue;
                }

                int sum = nums[i] + nums[l] + nums[r];
                if(sum == target) return sum;
                if(Math.abs(sum - target) < Math.abs(res - target)) res = sum;

                if(sum < target){
                    l++;
                }else{//sum >= target
                    r--;
                }
            }
        }
        return res;
    }
}