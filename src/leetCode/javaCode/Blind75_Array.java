package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_Array {

    //Time Complexity: O(n), Space Complexity: O(n)
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> remember = new HashMap<Integer, Integer>();
            for(int i=0; i<nums.length; i++){
                if(remember.containsKey(target - nums[i])){
                    return new int[]{i, remember.get(target - nums[i])};
                }else{
                    remember.put(nums[i], i);
                }
            }

            return new int[]{};
        }
    }

    //Time Complexity: O(n), Space Complexity: O(n)
    class Solution121 {
        public int maxProfit(int[] prices) {
            int min = Integer.MAX_VALUE;
            int profit = 0;

            for(int i=0; i<prices.length; i++){
                if(prices[i] < min){
                    min = prices[i];
                }else if(prices[i] - min > profit){
                    profit = prices[i] - min;
                }
            }

            return profit;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution217 {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> remember = new HashSet<Integer>();
            for(int i=0; i<nums.length; i++){
                if(remember.contains(nums[i])){
                    return true;
                }else{
                    remember.add(nums[i]);
                }
            }
            return false;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution238 {
        public int[] productExceptSelf(int[] nums) {
            //禁止使用除法
            int[] result = new int[nums.length];

            //初始化必設為1
            Arrays.fill(result, 1);

            //從左到右往前乘
            int temp = 1;
            for(int i=0; i<nums.length; i++){
                result[i] = temp;
                temp *= nums[i];//乘上這一個之後帶到後面, 越往後越大
            }

            //從右到左往後乘
            temp = 1;
            for(int i = nums.length - 1; i >= 0; i--){
                result[i] *= temp;
                temp *= nums[i];//乘上這一個之後帶到前面, 越往前越大
            }

            return result;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution53 {
        public int maxSubArray(int[] nums) {
            int max = Integer.MIN_VALUE;
            int endHereMax = 0;//用來記錄到目前為止的最大值, 甚麼都沒加就是0
            //Kadane算法
            for(int i=0; i<nums.length; i++){
                //比較一下這一個跟目前為止加上的, 哪個更大, 如果更大就捨棄先前加的部分
                endHereMax = Math.max(nums[i], endHereMax + nums[i]);
                max = Math.max(max, endHereMax);
            }

            return max;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution152 {
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE;
            int endHereMax = 1;//目前為止的最大, 不論甚麼數*1都會是自己, 所以可以設為1
            int endHereMin = 1;//目前為止的最小

            //類似Kadane算法, 但同時考慮到最小的情況, 有可能因為負負得正變成最大的
            for(int i=0; i<nums.length; i++){
                int temp = endHereMax;
                endHereMax = Math.max(nums[i], Math.max(endHereMax * nums[i], endHereMin * nums[i]));
                endHereMin = Math.min(nums[i], Math.min(temp * nums[i], endHereMin * nums[i]));
                max = Math.max(max, endHereMax);
            }

            return max;
        }
    }

    //Time Complexity: O(logn), Space Complexity: O(1)
    class Solution153 {
        public int findMin(int[] nums) {
            //使用二分法
            //分成兩翼, 左翼是由中到最大, 右翼是由最小到中, 要找的是左翼的最大+1
            int left = 0;
            int right = nums.length - 1;

            while(left < right){
                int mid = (left + right) / 2;

                if(nums[mid] < nums[right]){
                    //最右的比指標大, 代表指標在右翼
                    //[4, 5, 6, 7, 0, 1, 2]
                    //             ^mid  ^right
                    right = mid;//捨棄不包含自己的右側, 要保留自己因為可能是最小
                }else{
                    //最右的比指標小, 代表指標在左翼
                    //[4, 5, 6, 7, 0, 1, 2]
                    //    ^mid           ^right
                    left = mid + 1;//捨棄包含自己的左側
                }
            }

            return nums[right];
        }
    }

    //Time Complexity: O(logn), Space Complexity: O(1)
    class Solution33 {
        public int search(int[] nums, int target) {
            //二分法, 左右指標

            int left = 0;
            int right = nums.length - 1;

            //有可能會相等，因為是直接 + 1 或 - 1
            while(left <= right){
                int mid = (left + right) / 2;

                if(target == nums[mid])
                    return mid;

                if(nums[mid] < nums[right]){
                    //mid在右翼的情況
                    if(nums[mid] < target && target <= nums[right]){
                        left = mid + 1;//left - mid - target - right
                    }else{
                        right = mid - 1;//left - target - mid - right
                    }
                }else{
                    //mid在左翼的情況
                    if(nums[left] <= target && target < nums[mid]){
                        right = mid - 1;//left - target - mid - right
                    }else{
                        left = mid + 1;//left - mid - target - right
                    }
                }
            }

            return -1;
        }
    }

    //Time Complexity: O(n^2), Space Complexity: O(n), 空間複雜度主要是排序, 可能為O(n)
    class Solution15 {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();

            //!!記得要sort
            Arrays.sort(nums);

            for(int i = 0; i < nums.length - 2; i++){
                //重複的skip
                if(i > 0 && nums[i] == nums[i - 1])
                    continue;

                int left = i + 1;
                int right = nums.length - 1;

                while(left < right){
                    //重複的skip
                    if(left > i + 1 && nums[left] == nums[left - 1]){
                        left++;
                        continue;
                    }
                    if(right < nums.length - 1 && nums[right] == nums[right + 1]){
                        right--;
                        continue;
                    }

                    int sum = nums[i] + nums[left] + nums[right];
                    if(sum == 0){
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        res.add(temp);
                    }

                    if(sum > 0){
                        right--;//too big
                    }else{
                        left++;//too small
                    }
                }
            }

            return res;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution11 {
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int max = 0;

            while(left < right){
                //高度為左右兩側最小的
                int volume = (right - left) * Math.min(height[left], height[right]);
                max = Math.max(max, volume);

                if(height[left] < height[right]){
                    left++;
                }else{
                    right--;
                }
            }

            return max;
        }
    }

}