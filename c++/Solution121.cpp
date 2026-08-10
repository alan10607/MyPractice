//Slide Window Greedy Stock O(n) O(1), n = prices.size()
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
看起來要窮舉需要O(n^2)時間, 但可以透過Slide Window/Greedy的思維降為O(n)

l: 目前最低買入價格
r: 目前賣出價格

若 prices[r] < prices[l]，
則更新買入點: l = r

否則假設今天賣出, 更新最大獲利:
profit = prices[r] - prices[l]

每個元素只遍歷一次。
*/