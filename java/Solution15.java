package leetCode.java;

import java.util.*;

//LR Pointer O(n^2) O(logn)
class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // 記得要先排序過

        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    ++l;
                    continue;
                }
                if (r < n - 1 && nums[r] == nums[r + 1]) {
                    --r;
                    continue;
                }
                if (nums[i] + nums[l] + nums[r] == 0) {
                    res.add(new ArrayList<>(List.of(nums[i], nums[l], nums[r])));
                }
                
                if (nums[i] + nums[l] + nums[r] <= 0) { // 相同也要繼續移動
                    ++l;
                } else {
                    --r;
                }
            }
        }
        return res;
    }
}