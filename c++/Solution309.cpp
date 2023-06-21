//2D-DP O(n) O(1)
class Solution309 {
public:
    int maxProfit(vector<int>& prices) {
        int buy = -prices[0], sell = 0, cool = 0;
        for(int i=1; i<prices.size(); ++i){
            int newBuy = max(cool - prices[i], buy);
            int newSell = buy + prices[i];
            int newCool = max(sell, cool);
            buy = newBuy;
            sell = newSell;
            cool = newCool;
        }
        return max(sell, cool);
    }
};
/*
                cool
        buy             cool
    sell    buy
    cool
*/