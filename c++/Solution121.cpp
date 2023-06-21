//Slide Window O(n) O(1)
class Solution121 {
public:
    int maxProfit(vector<int>& prices) {
        int res = 0;
        int min = prices[0];
        for(int i=1; i<prices.size(); ++i){
            if(prices[i] < min){
                min = prices[i];
            }else{
                res = max(res, prices[i] - min);
            }
        }
        return res;
    }
};