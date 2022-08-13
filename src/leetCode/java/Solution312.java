package leetCode.java;

//2D-DP O(n^3) O(n^2)
class Solution312 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] val = new int[n + 2];
        int[][] dp = new int[n + 2][n + 2];//表示i到j的最大值可能
        for(int i=0; i<val.length; i++)//新增左右兩側1方便算
            val[i] = (i == 0 || i == val.length - 1) ? 1 : nums[i - 1];

        for(int gap=0; gap<n; gap++){//gap從0~n-1
            for(int i=1; i <= n - gap; i++){
                int j = i + gap;
                for(int k=i; k<=j; k++){//1-i-k-j-1
                    int sum = dp[i][k - 1] + val[i - 1] * val[k] * val[j + 1] + dp[k + 1][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }

        return dp[1][n];
    }
}
/* nums = [3,1,5,8] 從最後反推, 才能夠知道dp[i][k]與dp[k][j]是多少
dp順序要特別設計才會符合需求

       0 1 2 3 4 5
val = [1,3,1,5,8,1]
dp順序: 3 1 5 8 31 15 58 315 158 3158
dp[1][1] -> dp[2][2] -> dp[3][3] -> dp[4][4] ->
dp[1][2] -> dp[2][3] -> dp[3][4] ->
dp[1][3] -> dp[2][4] ->
dp[1][4]
*/