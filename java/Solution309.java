package leetCode.java;

//2D-DP O(n) O(1)
class Solution309 {
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