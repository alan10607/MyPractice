package leetCode.java;

//DP KP O(mn) O(n), m = coins.length, n = amount,
class Solution518 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];//代表n金額可以有幾種金幣組合
        dp[0] = 1;

        for(int coin : coins){
            for(int i=coin; i<=amount; i++){
                dp[i] += dp[i - coin];//繼承去掉這枚硬幣的所有方法
            }
        }
        return dp[amount];
    }
}

/* amount = 5, coins = [1,2,5]
重複的組合算一種

c\a 0   1   2   3   4   5
1   1   1   1   1   1   1
2   1   1   2   2   3   3
5   1   1   2   2   3   4

*/