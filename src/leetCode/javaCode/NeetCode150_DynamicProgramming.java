package leetCode.javaCode;

import java.util.*;

/**
 *  1-D Dynamic Programming
 *  2-D Dynamic Programming
 */
public class NeetCode150_DynamicProgramming {

    //Time Complexity: O(n), Space Complexity: O(1), n = cost.length
    //DP
    class Solution746 {
        public int minCostClimbingStairs(int[] cost) {
            //can either climb one or two steps
            int one = 0;//前兩格都可以直接跨過
            int two = 0;

            //跨過n格, 要走到dp[n]
            for(int i=2; i<=cost.length; i++){
                int temp = one;
                one = Math.min(one + cost[i - 1], two + cost[i - 2]);
                two = temp;//滾動
            }
            return one;
        }
    }
    /*
    cost = [10,15,20]
    dp為前一或二格到達所需的值, 加上該格所需要的值

    dp[0] = 0
    dp[1] = 0
    dp[2] = min(dp[0] + cost[0], dp[1] + cost[1]) = min(10, 15) = 10
    dp[3] = min(dp[1] + cost[1], dp[2] + cost[2]) = min(15, 30) = 15
    ...
    dp[n] = min(dp[n-2] + cost[n-2], dp[n-1] + cost[n-1])
    */

    //2D-DP
    class Solution416 {
        //Time Complexity: O(n^2), Space Complexity: O(n), O(n^2) = O(n * target), 其實target就是n的一半
        public boolean canPartition(int[] nums) {
            //用DFS(O(2^n))會太慢
            int sum = 0;
            for(int num : nums)
                sum += num;

            if(sum % 2 != 0) return false;

            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];//dp[i]表示是否能從nums取出若干數其總合為i
            dp[0] = true;//都不取就是0
            for(int num : nums){
                //要從target回到num, num <= i <= target
                for(int i = target; i >= num; i--){
                    dp[i] |= dp[i - num];//與num gap進行or
                }
            }
            return dp[target];
        }
        /*nums = [1,5,11,5] target = 11
              0				 5	            10
        dp = [T,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ]
        dp = [T, T,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ] num = 1
        dp = [T, T,  ,  ,  , T, T,  ,  ,  ,  ,  ] num = 5
        dp = [T, T,  ,  ,  , T, T,  ,  ,  ,  , T] num = 11
        dp = [T, T,  ,  ,  , T, T,  ,  ,  , T, T] num = 5

        因為不能重複, 若第二個for沒有逆序
        dp = [T,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ,  ]
        dp = [T, T, T, T, T, T, T, T, T, T, T, T] num = 1
        顯然是錯的
        */

        //Time Complexity: O(n sum(nums)), Space Complexity: O(sum(nums)), dp為cache, 最多有可能有nums的總和
        public boolean canPartition2(int[] nums) {
            //這個版本直接把使用1-0問題DFS加上cache來處理, cache避免重複的路線
            int sum = 0;
            for(int num : nums)
                sum += num;

            if(sum % 2 != 0) return false;

            int target = sum / 2;
            Set<Integer> dp = new HashSet<>();
            dp.add(0);//把空集合加入一開始
            for(int num : nums){
                Set<Integer> newDp = new HashSet<>(dp);

                for(int dpNum : dp){
                    if(dpNum + num == target)
                        return true;//成功組合

                    newDp.add(dpNum + num);//把這次的加總放入
                }

                dp = newDp;
            }
            return false;
        }
        /*
                                        0
                        1                               0
                6               1               5               0
            17      6      12       1      16       5      11       0
          22  17  11 6   17  12    5 1   21  16   10 5   17  11    5 0
        */
    }

    //Time Complexity: O(n), Space Complexity: O(n)
    //2D-DP
    class Solution309 {
        public int maxProfit(int[] prices) {
            //After selling stock, cooldown one day
            int buy = -prices[0];//第一天持股的話就要買prices[0]
            int sell = 0;//第一天不可能有賣出
            int rest = 0;//第一天休息則維持0

            for(int i=1; i<prices.length; i++){
                int newBuy = Math.max(buy, rest - prices[i]);//如果持有股票, 可能是之前就持有了, 或是從rest去買
                int newSell = buy + prices[i];//如果賣出股票而冷卻, 只可能是從buy去賣
                int newRest = Math.max(rest, sell);//如果目前無持股也不冷卻, 可能是之前就休息, 或是從剛從sell回復
                buy = newBuy;
                sell = newSell;
                rest = newRest;
            }
            return Math.max(sell, rest);//回傳最後的情況, 不可能是buy(因為-prices[])
        }
    }
    /*
    有三種狀態:
    rest = 不持有股票(一般期)
    buy = 持有股票
    sell = 不持有股票(冷卻期)

                     rest
            buy				  rest
        sell	buy		 buy	   rest
        rest sell buy  sell buy  buy rest
    */

    //2D-DP
    class Solution518 {
        //Time Complexity: O(mn), Space Complexity: O(n) m = coins.length, n = amount,
        public int change(int amount, int[] coins) {
            //從2D-dp壓縮成1D, 因為我們可以由左而右, 由上往下連加
            int[] dp = new int[amount + 1];//代表n金額可以有幾種金幣組合

            dp[0] = 1;
            for(int coin : coins){
                for(int i=coin; i<=amount; i++){
                    dp[i] += dp[i - coin];
                }
            }
            return dp[amount];
        }
        /*
        amount = 5, coins = [1,2,5] 本題可以重複
        c\a	0	1	2	3	4	5
        1	1	1	1	1	1	1
        2	1	1	2	2	3	3
        5	1	1	2	2	3	4
        */

        //Time Complexity: O(mn), Space Complexity: O(mn) m = coins.length, n = amount,
        public int change2(int amount, int[] coins) {
            //Memoization, 透過cache
            List<HashMap<Integer, Integer>> dp = new ArrayList<>();//<[<total, cache>], ...>
            for(int i=0; i<coins.length; i++)
                dp.add(new HashMap<Integer, Integer>());

            return dfs(amount, coins, 0, 0, dp);
        }

        public int dfs(int amount, int[] coins, int level, int total,  List<HashMap<Integer, Integer>> dp){
            if(total == amount) return 1;//紀錄為一次成功
            if(total > amount) return 0;//超過
            if(level == coins.length) return 0;//已經用完全部可能
            if(dp.get(level).containsKey(total)) return dp.get(level).get(total);

            int sum = dfs(amount, coins, level, total + coins[level], dp)//繼續加上這個coin
                    + dfs(amount, coins, level + 1, total, dp);//跳過這個coin
            dp.get(level).put(total, sum);//加入cache

            return sum;
        }
        /*  amount = 5, coins = [1,2,5]
        每次分支可分為繼續使用這個coin或跳到下一個coin
                        0
                1               0
            2       0       2       0
          3   2   2   0   4   0   5   0
          ...
        */
    }

    //2D-DP
    class Solution494 {
        //Time Complexity: O(mn), Space Complexity: O(n) m = nums.length, n = sumOfPosi,
        public int findTargetSumWays(int[] nums, int target) {
            //sum(正數) - sum(負數) = target
            //2 * sum(正數) = target + sum(負數) + sum(正數) = target + sum(nums)
            int sum = 0;
            for(int num : nums)
                sum += num;

            //sum(正數)=sumOfPosi必須存在, 且不可能為負數
            if((target + sum) % 2 != 0 || (target + sum) < 0) return 0;

            int sumOfPosi = (target + sum) / 2;

            int[] dp = new int[sumOfPosi + 1];//dp[i]表示從nums取出若干數其總合為i, 其組合數有多少
            dp[0] = 1;//都不取就是0
            for(int num : nums){
                for(int i = sumOfPosi; i >= num; i--){
                    dp[i] += dp[i - num];
                }
            }
            return dp[sumOfPosi];
        }
        /*  nums = [1,1,1,1,1], target = 3
        2*sum(posi) = target + sum(nums) = 8
        sum(posi) = 4, 正數的部分加起來要有4

            0	1	2   3   4
        1	1	1   0	0   0
        1	1	2	1   0   0
        1	1	3	3   1   0
        1	1	4	6   4   1
        1	1	5	10  10  5
        */

        //Time Complexity: O(mn), Space Complexity: O(mn) m = index = nums.length, n = total = sum(nums)
        public int findTargetSumWays2(int[] nums, int target) {
            //Memoization, 透過cache
            List<Map<Integer, Integer>> dp = new ArrayList<>();//<<total, 可以走的路線數>, ...>
            for(int i=0; i<nums.length; i++)
                dp.add(new HashMap<Integer, Integer>());

            return backtracking(0, 0, dp, nums, target);
        }

        public int backtracking(int i, int total, List<Map<Integer, Integer>> dp, int[] nums, int target){
            if(i == nums.length) return total == target ? 1 : 0;//走到底, 回傳是不是正確
            if(dp.get(i).containsKey(total)) return dp.get(i).get(total);

            //把可以走的可能記錄到dp
            int backtrack = backtracking(i + 1, total + nums[i], dp, nums, target)
                          + backtracking(i + 1, total - nums[i], dp, nums, target);
            dp.get(i).put(total, backtrack);

            return backtrack;
        }
        /* nums = [1,1], target = 2
                    0
        i=1		1		-1
        i=2	  2   0   0   -2
        */
    }

    //Time Complexity: O(mn), Space Complexity: O(mn)
    //2D-DP
    class Solution97 {
        public boolean isInterleave(String s1, String s2, String s3) {
            int m = s1.length();
            int n = s2.length();
            if(m + n != s3.length()) return false;

            boolean[][] dp = new boolean[m + 1][n + 1];

            dp[0][0] = true;//空字串一定有辦法湊出
            for(int i=1; i<=m; i++)
                dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);//上一個是連上的, 而且這個也是

            for(int j=1; j<=n; j++)
                dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);//上一個是連上的, 而且這個也是

            for(int i=1; i<=m; i++){
                for(int j=1; j<=n; j++){
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
            return dp[m][n];
        }
    }
    /* "aab", s2 = "dbc", s3 = "aadbbc"
                d   b   c
            T   F   F   F
        a   T   F   F   F
        a   T   T   T   F
        b   F   T   T   T
    */

    //Time Complexity: O(mn), Space Complexity: O(mn)
    //2D-DP
    class Solution329 {
        public int longestIncreasingPath(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] dp = new int[m][n];
            for(int i=0; i<m; i++)
                Arrays.fill(dp[i], -1);//預設為-1, 0 <= matrix[i][j] <= 231 - 1

            int res = 0;

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    res = Math.max(res, dfs(i, j, dp, matrix, -1));
                }
            }
            return res;
        }

        public int dfs(int i, int j, int[][] dp, int[][] matrix, int last){
            if(matrix[i][j] <= last) return 0;
            if(dp[i][j] != -1) return dp[i][j];//如果已經走過了, 直接回覆dp

            last = matrix[i][j];

            int m = matrix.length;
            int n = matrix[0].length;
            int lip = 0;
            if(i + 1 <  m) lip = Math.max(lip, dfs(i + 1, j, dp, matrix, last));
            if(i - 1 >= 0) lip = Math.max(lip, dfs(i - 1, j, dp, matrix, last));
            if(j + 1 <  n) lip = Math.max(lip, dfs(i, j + 1, dp, matrix, last));
            if(j - 1 >= 0) lip = Math.max(lip, dfs(i, j - 1, dp, matrix, last));
            lip += 1;//自己也算1
            dp[i][j] = lip;
            return lip;
        }
    }
    /*
    9	9	4
    6	6	8
    2	1	1

    LIP:
    1	1	2
    2	2	1
    3	4	2
    */

    //Time Complexity: O(mn), Space Complexity: O(mn)
    //2D-DP
    class Solution115 {
        public int numDistinct(String s, String t) {
            int m = s.length();
            int n = t.length();
            int[][] dp = new int[m + 1][n + 1];

            //空字串除了空字串都滿足不了t
            for(int i=0; i<=m; i++)
                dp[i][0] = 1;//所有的s只要不取都可以滿足空字串

            for(int i=1; i<=m; i++){
                for(int j=1; j<=n; j++){
                    if(s.charAt(i - 1) == t.charAt(j - 1)){
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                    }else{
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[m][n];
        }
    }
    /* s = "babgbag", t = "bag"
    若匹配不合, 則維持上一次可以的機會
    若匹配相同, 可以選擇取該值(加上之前的機會)或不取(維持上一次)

            b   a   g
        1   0   0   0
    b   1   1   0   0
    a   1   1   1   0
    b   1   2   1   0
    g   1   2   1   1
    b   1   3   1   1
    a   1   3   4   1
    g   1   3   4   5
    */

    //Time Complexity: O(mn), Space Complexity: O(mn)
    //2D-DP
    class Solution72 {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            int[][] dp = new int[m + 1][n + 1];

            for(int i=1; i<=m; i++)
                dp[i][0] = i;//要變成空字串依序要刪除i個

            for(int j=1; j<=n; j++)
                dp[0][j] = j;//空字串要變成別的字要依序新增j個

            for(int i=1; i<=m; i++){
                for(int j=1; j<=n; j++){
                    if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                        //無異動
                        dp[i][j] = dp[i - 1][j - 1];
                    }else{
                        //有異動
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));//記得要+1
                    }
                }
            }
            return dp[m][n];
        }
    }
    /* word1 = "horse", word2 = "ros"

    如果charAt(i)==charAt(j), 則i跟j都+1往後, dp[i][j] = dp[i-1][j-1]
    如果charAt(i)!=charAt(j), 可以選擇: (取其中最小+1, 因為要做改變)
    Insert: i不變, j+1, dp[i][j] = dp[i-1][j]
    Delete: i+1, j不變, dp[i][j] = dp[i][j-1]
    Replace: i+1, j+1, dp[i][j] = dp[i-1][j-1]

            r   o   s
        0.  1   2   3
    h   1   1.  2   3
    o   2   2   1.  2
    r   3   2   2.  2
    s   4   3   3   2.
    e   5   4   4   3.

    horse
    rorse
    rorse
    rose
    rose
    ros
    */

    //Time Complexity: O(n^3), Space Complexity: O(n^2)
    //2D-DP
    class Solution312 {
        public int maxCoins(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n + 2][n + 2];//int[l][r]表示l到r之前的最大可能
            int[] tNums = new int[n + 2];//頭尾加上1方便計算
            for(int i=0; i < tNums.length; i++)
                tNums[i] = (i == 0 || i == tNums.length - 1) ? 1 : nums[i - 1];

            //要把迴圈設定做調整
            for (int gap = 0; gap < n; gap++) {//代表跑dp[l][l+gap]的範圍
                for (int l=1; l <= (tNums.length - 2) - gap; l++) {//設定右界為r - gap
                    int r = l + gap;
                    for (int k = l; k <= r; k++) {//遍歷l~r的所有可能, 取最大可能
                        int sum = tNums[l - 1] * tNums[k] * tNums[r + 1];
                        sum += dp[l][k - 1] + dp[k + 1][r];
                        dp[l][r] = Math.max(dp[l][r], sum);
                    }
                }
            }
            return dp[1][tNums.length - 2];
        }

        public int maxCoins2(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n + 2][n + 2];//int[l][r]表示l到r之前的最大可能
            int[] newNums = new int[n + 2];//頭尾加上1方便計算
            for(int i=0; i < newNums.length; i++)
                newNums[i] = (i == 0 || i == newNums.length - 1) ? 1 : nums[i - 1];

            return dfs(1, newNums.length - 2, dp, newNums);
        }

        public int dfs(int l, int r, int[][] dp, int[] nums){
            if(l > r) return 0;//不存在
            if(dp[l][r] > 0) return dp[l][r];

            int total = 0;
            for(int i=l; i<=r; i++){
                //找出最大的可能
                total = Math.max(total, nums[l - 1] * nums[i] * nums[r + 1] //最後一個破的氣球
                        + dfs(l, i - 1, dp, nums)//左方的可能
                        + dfs(i + 1, r, dp, nums));//右方的可能
            }
            dp[l][r] = total;//Memoization, 透過cache
            return total;
        }
    }
    /* nums = [3,1,5]
    1   3   1   5   1

    0   0   0   0   0
    0   3   30  35  0
    0   0   15  30  0
    0   0   0   5   0
    0   0   0   0   0
    */

    //Time Complexity: O(mn), Space Complexity: O(mn), m = s.length(), n = p.length()
    //2D-DP
    class Solution10 {
        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();
            boolean[][] dp = new boolean[m + 1][n + 1];//dp[i][j]表示s[0, i)與p[0, j)是否match
            dp[0][0] = true;

            for (int i = 0; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (j > 1 && p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i][j - 2] //跳過*, 參考j-2的那個
                                || (i > 0 //空白無法配對
                                && dp[i - 1][j] //參考上一個
                                && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                    } else {
                        dp[i][j] = i > 0 //空白無法配對
                                && dp[i - 1][j - 1] //參考上一個
                                && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                    }
                }
            }
            return dp[m][n];
        }
        /*
        有三種情況:
        1. 不含'*', 則只要dp[i-1][j-1]且(s.char(i)==p.char(j) || p.char(j)=='.')就可以
        2. 含'*', 但不使用, 則直接參考dp[i][j-2], j跳過2 chars
        3. 含'*', 且使用, 則要dp[i-1][j-1]且(s.char(i)==p.char(j-1) || p.char(j-1)=='.')就可以
        實際上因為dp多加空格, 所以charAt都要再多-1

        s = "aab", p = "a*b"

                a   *   b
            T   F   T   F
        a   F   T   T   F
        a   F   F   T   F
        b   F   F   F   T
        */

        public boolean isMatch2(String s, String p) {
            //使用backtracking, 稍慢
            if(p.isEmpty()) return s.isEmpty();

            if(p.length() > 1 && p.charAt(1) == '*'){
                return isMatch2(s, p.substring(2))//不使用
                        || (!s.isEmpty() //使用*
                        && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                        && isMatch2(s.substring(1), p));
            }else{
                return !s.isEmpty()
                        && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                        && isMatch2(s.substring(1), p.substring(1));
            }
        }
    }

}