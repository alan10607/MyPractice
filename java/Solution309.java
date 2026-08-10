package leetCode.java;

//DP Stock O(n) O(n), n = prices.size()
class Solution309 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2]; // //[第i+1天][0/1=是/否持有股票]
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            // 賣出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);

            // 買入
            if (i == 1) {
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); // i==1時dp[i - 2][0]不存在, 即不存在兩天前不持有股票的狀態
            } else {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]); // 有cool要拿前天的資料買入
            }
        }
        return dp[n - 1][0];
    }
}


//DP Stock O(n) O(n), n = prices.size()
class Solution309_2 {
    public int maxProfit(int[] prices) {
        //After selling the stock need cooldown one day
        int free = 0;
        int buy = -prices[0];
        int sell = 0;
        for(int i=1; i<prices.length; i++){
            int newFree = Math.max(free, sell);
            int newBuy = Math.max(buy, free - prices[i]);
            int newSell = buy + prices[i];
            free = newFree;
            buy = newBuy;
            sell = newSell;
        }
        return Math.max(free, sell);//free, sell > buy
    }
}
/*
3種狀態:
free: 可能是之前就free或是sell之後(要冷卻一天)
buy:  可能是之前free買進或本來就是buy
sell: 只可能是buy之後

				free
		buy				free
	sell	buy
	free
*/