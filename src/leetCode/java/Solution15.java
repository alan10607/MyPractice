package leetCode.java;

import java.util.*;

//LR Pointer O(n^2) O(n)
class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);//記得要先排序過

        List<List<Integer>> res = new ArrayList<>();
        for(int i=0; i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1])
                continue;//跳過重複

            int l = i + 1;
            int r = nums.length - 1;
            while(l < r){
                if(l > i + 1 && nums[l] == nums[l - 1]){
                    l++;//跳過重複
                    continue;
                }

                if(r < nums.length - 1 && nums[r] == nums[r + 1]){
                    r--;//跳過重複
                    continue;
                }

                int sum = nums[i] + nums[l] + nums[r];

                if(sum == 0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[l]);
                    temp.add(nums[r]);
                    res.add(temp);
                }

                if(sum > 0){
                    r--;
                }else{//sum == 0 || sum < 0
                    l++;
                }
            }
        }

        return res;
    }
}