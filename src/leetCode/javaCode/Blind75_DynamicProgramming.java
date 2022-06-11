package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_DynamicProgramming {

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution70 {
        public int climbStairs(int n) {
            //動態規劃
            int one = 1;
            int two = 1;
            for(int i = 0; i < n - 1; i++){
                int temp = one;
                one = one + two;//上一個跟上上個
                two = temp;//上上個也往前移
            }
            return one;

            /*
            4 -> 5   = 1 way
            3 -> 4|5 = dp[4] + 1 = 2
            2 -> 3|4 = dp[3] + dp[4] = 2 + 1 = 3
            1 -> 2|3 = dp[2] + dp[3] = 3 + 2 = 5
            0 -> 1|2 = dp[1] + dp[2] = 5 + 3 = 8, 0 -> 5共有8種方式

            n -> n+1|n+2 = dp[n+1]|dp[n+2] = dp[one] + dp[two]
            */
        }
    }

    //Time Complexity: O(nm), Space Complexity: O(n), n是amount大小, m是硬幣組合數coins.length
    class Solution322 {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];//每種金額的的最少硬幣解法

            //硬幣最多可能是amount個$1, 先設定為超出大值, 如果沒被異動代表目標值超過所有硬幣組合
            Arrays.fill(dp, amount + 1);

            dp[0] = 0;//設定例外, 目標為0就不用硬幣
            for(int i=1; i<=amount; i++){
                for(int j=0; j<coins.length; j++){
                    //必須要至少等於或大於該coin面額
                    if(i >= coins[j])
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);//找出最少的步驟
                }
            }

            return dp[amount] > amount ? -1 : dp[amount];//超出最大可能, 則目標值超過所有硬幣組合, 回-1

            /* coins = [1,2,5], amount = 11, 計算需要“最少”由幾個硬幣組成
            dp[0] = 0 coins
            dp[1] = min(dp[1-1], dp[1-2], dp[1-5]) + 1 = 0 + 1 = 1
            dp[2] = min(dp[2-1], dp[2-2], dp[2-5]) + 1 = 0 + 1 = 1
            dp[3] = min(dp[3-1], dp[3-2], dp[3-5]) + 1 = 1 + 1 = 2
            dp[4] = min(dp[4-1], dp[4-2], dp[1-5]) + 1 = 1 + 1 = 2
            dp[5] = min(dp[5-1], dp[5-2], dp[5-5]) + 1 = 0 + 1 = 1
            dp[6] = min(dp[6-1], dp[6-2], dp[6-5]) + 1 = 1 + 1 = 2
            dp[7] = min(dp[7-1], dp[7-2], dp[7-5]) + 1 = 1 + 1 = 2
            dp[8] = min(dp[8-1], dp[8-2], dp[8-5]) + 1 = 2 + 1 = 3
            dp[9] = min(dp[9-1], dp[9-2], dp[9-5]) + 1 = 2 + 1 = 3
            dp[10] = min(dp[10-1], dp[10-2], dp[10-5]) + 1 = 1 + 1 = 2
            dp[11] = min(dp[11-1], dp[11-2], dp[11-5]) + 1 = 2 + 1 = 3
            */
        }
    }

    //Time Complexity: O(n^2), Space Complexity: O(n)
    class Solution300 {
        public int lengthOfLIS(int[] nums) {
            //設定例外
            if(nums.length == 0) return 0;

            int[] dp = new int[nums.length];
            int res = 1;//除了nums.length == 0 以外至少有1
            dp[0] = 1;//預設值為自己本身

            //計算從0到自己可以有多少LIS
            for(int i=1; i<nums.length; i++){
                dp[i] = 1;//預設為 1
                for(int j = i - 1; j >= 0; j--){
                    //這次的數要大於之前的數
                    if(nums[i] > nums[j])
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
                res = Math.max(res, dp[i]);//紀錄下最大的dp
            }

            return res;

            /*
            [1, 2, 4, 3], 計算到達的最多Increasing Subsequence
            dp[0] = 1 (自己本身)
            dp[1] = max(1, 1 + dp[0]) = 2
            dp[2] = max(1, 1 + dp[0], 1 + dp[1]) = 3
            dp[3] = max(1, 1 + dp[0], 1 + dp[1]) = 3, nums[2] > nums[3]所以不能用dp[2]
            LIS = max(all dp[]) = 3
            */
        }
    }

    //Time Complexity: O(mn), Space Complexity: O(mn), m, n = text1, text2's length
    class Solution1143 {
        public int longestCommonSubsequence(String text1, String text2) {
            //兩個text長度是不固定的, 用2D-dp

            //要判斷邊界 i - 1 < 0的情況, 乾脆直接讓arrays多一格
            int[][] dp = new int[text1.length() + 1][text2.length() + 1];

            for(int i = 1; i < text1.length() + 1; i++){
                for(int j = 1; j < text2.length() + 1; j++){
                    //判斷cahrAt記得-1回來
                    if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                        dp[i][j] = dp[i - 1][j - 1] + 1;//相等的話, 直接從對角線+1
                    }else{
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);//不同的話, 取上方或左方對大值
                    }
                }
            }

            return dp[text1.length()][text2.length()];

            /* 一層層跑完右下就會獲得最大值
                    a   c   e
                0	0	0	0
            a   0	1	1	1
            b	0	1	1	1
            c	0	1	2	2
            d	0	1	2	2
            e	0	1	2	3
            */
        }
    }

    //Time Complexity: O(n^2), Space Complexity: O(n), n = s.length()
    class Solution139 {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordDictSet = new HashSet(wordDict);//O(n) to O(1)
            //有可能s被重複使用, 故用dp
            boolean[] dp = new boolean[s.length() + 1];

            dp[0] = true;//空字串也符合
            for(int i=1; i<=s.length(); i++){
                //給定j為小於i的指標, 從0開始
                // leetcode
                // ^j  ^i
                for(int j = 0; j < i; j++){
                    if(dp[j] && wordDictSet.contains(s.substring(j, i))){
                        dp[i] = true;
                        break;//是 or , 跳出迴圈
                    }
                }
            }

            return dp[s.length()];

            /*
            "leetcode", ["leet", "code"]
            dp[0] = true, 空字串也符合
            dp[1] = (dp[0] && s.substring(0, 1)) = false
            dp[2] = (dp[0] && s.substring(0, 2)) || (dp[1] && s.substring(1, 2)) = false
            dp[3] = (dp[0] && s.substring(0, 3)) || (dp[1] && s.substring(1, 3))
                 || (dp[2] && s.substring(2, 3)) = false
            dp[4] = (dp[0] && s.substring(0, 4)) || (dp[1] && s.substring(1, 4))
                 || (dp[2] && s.substring(2, 4)) || (dp[3] && s.substring(2, 4)) = true
            ...
            dp[n] = (dp[0] && s.substring(0, n)) || ... (dp[n - 1] && s.substring(n - 1, n))
            */
        }
    }

    //Time Complexity: O(mn), Space Complexity: O(n), n = target, m = nums.length
    class Solution377 {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];

            dp[0] = 1;//初始化, 0只有一種方法就是為空
            for(int i=1; i<=target; i++){
                for(int num : nums){
                    if(i - num >= 0)//需判斷目前的數i比num大
                        dp[i] += dp[i - num];
                }
            }

            return dp[target];

            /*
            nums = [1,2,3], 每次增加target數字都會有更多可能
            dp[0] = () = 1, 目標為0只有一種方法就是為空
            dp[1] = (1 + dp[0]) = (1) = 1
            dp[2] = (1 + dp[1], 2 + dp[0]) = (11, 2) = 2
            sp[3] = (1 + dp[2], 2 + dp[1], 3 + dp[0])
                    = (111, 12, 21, 3) = 4
            dp[4] = (1 + dp[3], 2 + dp[2], 3 + dp[1])
                  = (1111, 112, 121, 13, 211, 22, 31) = 7
            dp[5] = (1 + dp[4], 2 + dp[3], 3 + dp[2], 4 + dp[1])
                  = (11111, 1112, 1121, 113, 1211, 122, 131
                     2111, 212, 221, 23, 311, 32, 41 ) = 14
                 ...
            dp[n] = (nums[0] + dp[n - nums[0]], nums[1] + dp[n - nums[1]], ...)

            */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution198 {
        public int rob(int[] nums) {
            int one = nums[0];
            int two = 0;

            //直接從1開始, 因為已經設one = nums[0]
            for(int i=1; i<nums.length; i++){
                int temp = one;
                one = Math.max(one, nums[i] + two);//比較前一個 或 這個+前二個哪個比較大
                two = temp;//滾動往前修正
            }

            return one;

            /*
            nums = [2,7,9,3,1]
            dp[0] = 2
            dp[1] = max(dp[0], nums[1]) = 7
            dp[2] = max(dp[1], nums[2] + dp[0]) = 11
            dp[3] = max(dp[2], nums[3] + dp[1]) = 11
            dp[4] = max(dp[3], nums[4] + dp[2]) = 12
            ...
            dp[n] = dp[n - 1], nums[n] + dp[n-2]
            始終只需要n - 1, n - 2就夠了
            */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution213 {
        public int rob(int[] nums) {
            //跟上一個差不多, 但頭尾相連
            //只有兩種情況: 有偷第一沒偷最後nums[0] ~ nums[n - 2]
            //, 或沒偷第一有偷最後nums[1] ~ nums[n - 1]
            //直接拆成兩種情況處理

            //設定例外條件, 題目已給 1 <= nums.length
            if(nums.length == 1) return nums[0];

            int n = nums.length;

            //使用Arrays.copyOfRange會多跑一次
            return Math.max(findRob(nums, 0, n - 2), findRob(nums, 1, n - 1));
        }

        public int findRob(int[] nums, int start, int end) {
            int one = nums[start];
            int two = 0;

            //直接從start + 1開始, 因為已經設one = nums[start]
            for(int i = start + 1; i <= end; i++){
                int temp = one;
                one = Math.max(one, nums[i] + two);
                two = temp;
            }

            return one;
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution91 {
        public int numDecodings(String s) {
            //設定例外, s只有可能'0' ~ '9'
            if(s.length() == 0) return 0;

            int one = 1;//必須設定為1, i=0為空也算是一種方式
            int two = 0;

            for(int i=1; i<=s.length(); i++){
                int newOne = 0;

                //1個數時, 只有'0'沒有應對英文
                if(s.charAt(i - 1) != '0')
                    newOne += one;

                //2個數時, 只有'10' - '26'有應對英文
                if(i > 1 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')))
                    newOne += two;

                two = one;
                one = newOne;
            }

            return one;

            /*
            s = "2262"
            dp[0] = () = 1 way //題目設定查無回0
            dp[1] = (dp[0] + s.substring(0, 1)) = (B) = (2) = 1
            dp[2] = (dp[1] + s.substring(1, 2), dp[0] + s.substring(0, 2)) = ((2)(2), (22)) = (BB, V)= 2
            dp[3] = (dp[2] + s.substring(2, 3), dp[1] + s.substring(1, 3))
                  = ((2)(2)(6), (22)(6), (2)(26)) = (BBF, VF, BZ) = 3
            dp[4] = (dp[3] + s.substring(3, 4), dp[2] + s.substring(2, 4))
                  = ((2)(2)(6)(2}, (22)(6)(2}, (2)(26)(2), (2)(2)<62>, (22)<62>) = (BBFB, VFB, BZB) = 3, 62不可行
            ...
            dp[n] = (dp[n - 1] + s.substring(n - 1, n), dp[n - 2] + s.substring(n - 2, n))
            */
        }
    }

    //Time Complexity: O(mn), Space Complexity: O(mn)
    class Solution62 {
        public int uniquePaths(int m, int n) {
            //2D-dp, 避免出格直接多一格
            int[][] dp = new int[m + 1][n + 1];

            //設定初始值
            for(int i=1; i<=m; i++)
                dp[i][1] = 1;

            for(int j=1; j<=n; j++)
                dp[1][j] = 1;

            for(int i=2; i<=m; i++){
                for(int j=2; j<=n; j++){
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }

            return dp[m][n];

            /*
            m = 3, n = 7, 找一下到那一格有幾種可能

                1	2	3	4	5	6	7
            1	1	1	1	1	1	1	1
            2	1	2	3	4	5	6	7
            3	1	3	6	10	15	21	28
            4	1	4	10	20	35	56	84
            */
        }
    }

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution55 {
        public boolean canJump(int[] nums) {
            int rightMost = 0;//目前能走到的最右邊, 先設為0

            for(int i=0; i<nums.length; i++){
                //若能走的到, 就更新能走到的最大右邊
                if(i <= rightMost){
                    rightMost = Math.max(rightMost, i + nums[i]);

                    //走到最右側了, 提早跳出
                    if(rightMost >= nums.length - 1)
                        return true;//原地踏步nums = [0], 也要算true
                }
            }

            return false;
        }
    }

}