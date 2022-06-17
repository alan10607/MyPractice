package leetCode.test2nd;

import java.util.*;

public class Blind75_Array {

    //HaspMap
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> memo = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                if (memo.containsKey(target - nums[i])) {
                    return new int[]{memo.get(target - nums[i]), i};
                } else {
                    memo.put(nums[i], i);
                }
            }
            return new int[]{};
        }
    }

    //*Slide window
    class Solution121 {
        public int maxProfit(int[] prices) {
            int res = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < prices.length; i++) {
                //若新的價格更小, 右滑左窗口更新為最小的
                if (prices[i] < min) {
                    min = prices[i];
                } else if (prices[i] - min > res) {
                    res = prices[i] - min;
                }
            }

            return res;
        }
    }

    //HashMap
    class Solution217 {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> memo = new HashSet<>();
            for (int num : nums) {
                if (memo.contains(num)) {
                    return true;
                } else {
                    memo.add(num);
                }
            }
            return false;
        }
    }

    //*Array
    class Solution238 {
        public int[] productExceptSelf(int[] nums) {
            int[] res = new int[nums.length];
            Arrays.fill(res, 1);//預設為1

            //從左到右
            int temp = 1;
            for (int i = 0; i < nums.length; i++) {
                res[i] = temp;
                temp *= nums[i];
            }

            //從右到左
            temp = 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                res[i] *= temp;
                temp *= nums[i];
            }

            return res;
        }
        /*
        1	2	3	4
        1	1	2	6
        24	12	8	6
        */
    }

    //*Kadane's algorithm
    class Solution53 {
        public int maxSubArray(int[] nums) {
            int max = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum = Math.max(nums[i], sum + nums[i]);//若現在更大, 捨棄之前
                max = Math.max(max, sum);
            }
            return max;
        }
    }

    //*Kadane's algorithm
    class Solution152 {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE;
            int product = 1;
            int negeProduct = 1;

            for(int i=0; i<nums.length; i++){
                int temp = product;
                product     = Math.max(Math.max(nums[i], product * nums[i]), negeProduct * nums[i]);
                negeProduct = Math.min(Math.min(nums[i], temp    * nums[i]), negeProduct * nums[i]);

                max = Math.max(max, product);
            }
            return max;
        }
    }

    //Binary search
    class Solution153 {
        public int findMin(int[] nums) {
            int l = 0;
            int r = nums.length - 1;

            while(l < r){
                int mid = (l + r) /2;

                //在右
                if(nums[mid] < nums[r]){
                    r = mid;
                }else{//在左
                    l = mid + 1;//排除這個, 相等時優先讓l移動, 因為要回傳r
                }
            }
            return nums[r];
        }
    }

    //*Binary search
    class Solution33 {
        public int search(int[] nums, int target) {
            int l = 0;
            int r = nums.length - 1;

            //考量到邊界, 有可能會等於
            while(l <= r){
                int mid = (l + r) / 2;

                if(nums[mid] == target)
                    return mid;

                //mid在右
                if(nums[mid] < nums[r]){
                    if(nums[mid] < target && target <= nums[r]){
                        l = mid + 1;//l-mid-target-r
                    }else{
                        r = mid - 1;//l-target-mid-r
                    }
                }else{//在左
                    if(nums[l] <= target && target < nums[mid]){
                        r = mid - 1;//l-target-mid-r
                    }else{
                        l = mid + 1;//l-mid-target-r
                    }
                }

            }
            return -1;
        }
    }

    //LR Pointer
    class Solution15 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);

            List<List<Integer>> res = new ArrayList<>();
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
                    if(sum == 0){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[l]);
                        temp.add(nums[r]);
                        res.add(temp);
                        l++;//記得=0也要移動指標
                    }

                    if(sum > 0) r--;
                    if(sum < 0) l++;
                }
            }
            return res;
        }
    }

    //LR Pointer
    class Solution11 {
        public int maxArea(int[] height) {
            int l = 0;
            int r = height.length - 1;
            int max = 0;

            while(l < r){
                int area = (r - l) * Math.min(height[l], height[r]);
                max = Math.max(max, area);

                if(height[l] < height[r]){
                    l++;
                }else{
                    r--;
                }
            }

            return max;
        }
    }

}