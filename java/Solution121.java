package leetCode.java;

//Slide Window Greedy O(n) O(1), n = prices.size()
class Solution121 {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int res = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] < minPrice) {
                minPrice = prices[i]; // 比minPrice更低的價格, 尋求更低的買入價格
            } else {
                res = Math.max(res, prices[i] - minPrice); // 比minPrice更高的價格, 嘗試更高的獲利
            }
        }
        return res;
    }
}