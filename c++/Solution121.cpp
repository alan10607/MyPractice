//Slide Window DP O(n) O(1)
class Solution121 {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0, min_price = prices[0];
        for (int i = 1; i < prices.size(); ++i) {
            if (prices[i] < min_price) {
                min_price = prices[i];
            } else {
                res = max(res, prices[i] - min_price);
            }
        }
        return res;
    }
};
/*
所有可能 = {第x天買,第y天賣}
0 <= x < y, x < y < price.size()

res = max(所有可能)

看起來要窮舉需要O(n^2)時間, 但可以透過滑動窗口的思維降為O(n)
*/