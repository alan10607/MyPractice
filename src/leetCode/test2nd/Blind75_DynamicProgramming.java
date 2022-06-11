package leetCode.test2nd;

import java.util.*;

public class Blind75_DynamicProgramming {

    //DP
    class Solution70 {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];

            dp[0] = 1;
            dp[1] = 1;
            for(int i=2; i<=n; i++){
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }

    //*DP
    class Solution322 {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);//amount + 1表示無法組合成

            dp[0] = 0;
            for(int i=1; i<=amount; i++){
                for(int coin : coins){
                    if(i >= coin)
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }

            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

    //*DP
    class Solution300 {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int max = 0;

            for(int i=0; i<nums.length; i++){
                dp[i] = 1;//預設起點自己時長度為1
                for(int j=0; j<i; j++){
                    if(nums[i] > nums[j])
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(max, dp[i]);
            }
            return max;
        }
    }

    //2D-DP
    class Solution1143 {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length();
            int n = text2.length();
            int[][] dp = new int[m + 1][n + 1];

            for(int i=1; i<=m; i++){
                for(int j=1; j<=n; j++){
                    if(text1.charAt(i - 1) == text2.charAt(j - 1)){//charAt記得要 - 1
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }else{
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }
            return dp[m][n];
        }
    }

    //*DP
    class Solution139 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet(wordDict);//O(n) to O(1)
            boolean[] dp = new boolean[s.length() + 1];

            dp[0] = true;
            for(int i=1; i<=s.length(); i++){
                for(int j = i - 1; j>=0; j--){
                    if(wordDictSet.contains(s.substring(j, i)) && dp[j]){
                        dp[i] = true;
                        break;//要記得直接break
                    }
                }
            }
            return dp[s.length()];
        }
    }

    //DP
    class Solution377 {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];

            dp[0] = 1;
            for(int i=1; i<=target; i++){
                for(int num : nums){
                    if(i >= num)
                        dp[i] += dp[i - num];
                }
            }
            return dp[target];
        }
    }

    //DP
    class Solution198 {
        public int rob(int[] nums) {
            if(nums.length == 1) return nums[0];

            int one = nums[0];
            int two = 0;

            for(int i=1; i<nums.length; i++){
                int temp = one;
                one = Math.max(nums[i] + two, one);
                two = temp;
            }
            return one;
        }
    }

    //DP
    class Solution213 {
        public int rob(int[] nums) {
            if(nums.length == 1) return nums[0];
            if(nums.length == 2) return Math.max(nums[0], nums[1]);

            return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
        }

        public int rob(int[] nums, int start, int end){
            int one = nums[start];
            int two = 0;

            for(int i = start + 1; i<=end; i++){//記得外面給的邊界, 要有等於
                int temp = one;
                one = Math.max(nums[i] + two, one);//注意變數位置, one是上一個, two是上上個
                two = temp;
            }
            return one;
        }
    }

    //*DP
    class Solution91 {
        public int numDecodings(String s) {
            int[] dp = new int[s.length() + 1];

            dp[0] = 1;//設空字串也是一種方法
            for(int i=1; i<=s.length(); i++){
                //注意charAt()位置
                char thisCh = s.charAt(i - 1);
                if(thisCh != '0')//1~9
                    dp[i] += dp[i - 1];

                if(i > 1){
                    char lastCh = s.charAt(i - 2);
                    if(lastCh == '1' || (lastCh == '2' && thisCh <= '6'))//10~26
                        dp[i] += dp[i - 2];
                }
            }
            return dp[s.length()];
        }
    }

    //2D-DP
    class Solution62 {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];

            for(int i=0; i<m; i++)
                dp[i][0] = 1;

            for(int j=0; j<n; j++)
                dp[0][j] = 1;

            for(int i=1; i<m; i++){
                for(int j=1; j<n; j++){
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
            return dp[m - 1][n - 1];
        }
    }

    //*Greedy
    class Solution55 {
        public boolean canJump(int[] nums) {
            int toRight = 0;

            for(int i=0; i<nums.length; i++){
                if(i <= toRight){//需要有一個前提, 走的到
                    toRight = Math.max(toRight, i + nums[i]);
                    if(toRight >= nums.length - 1)//記得是位子要 - 1
                        return true;
                }
            }
            return false;
        }
    }

}