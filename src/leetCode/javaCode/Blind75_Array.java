package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
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

}