package leetCode.java;

//Slide window O(n) O(1)
class Solution121 {
    public int maxProfit(int[] prices) {
        int res = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<prices.length; i++){
            if(prices[i] < min){
                min = prices[i];
            }else{
                res = Math.max(res, prices[i] - min);
            }
        }
        return res;
    }
}