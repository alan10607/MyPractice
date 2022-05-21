package leetCode.javaCode;

import java.util.*;

/**
 @see <a href="https://neetcode.io/">參考教學</a>
 @see <a href="https://raymondjiang.net/2022/02/18/about-leetcode-blind-75/">參考教學</a>
 @see <a href="https://www.cnblogs.com/grandyang/p/5138186.html/">參考教學</a>
 */
public class Blind75_DynamicProgramming {

    //Time Complexity: O(n), Space Complexity: O(1)
    class Solution {
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

}