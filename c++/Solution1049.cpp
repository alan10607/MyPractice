//DP KP O(mn) O(n), m = stones.size(), n = target = sum / 2
class Solution1049 {
public:
    int lastStoneWeightII(vector<int>& stones) {
        int sum = 0;
        for(int stone : stones) sum += stone;

        int target = sum / 2;//捨棄餘數, 因為餘數會遺留
        vector<bool> dp(target + 1);//是否有辦法組合出i
        dp[0] = true;
        for(int stone : stones){
            for(int i=target; i>=stone; --i){//0-1背包問題
                dp[i] = dp[i] || dp[i - stone];
            }
        }

        for(int i = target; i>=0; --i){
            if(dp[i]) return sum - 2 * i;
        }

        return 0;
    }
};
/* 任意取兩個石頭, 只要答案最小就好
可以改題意為是否可組出n, n < sum / 2, 0-1背包問題
剩餘即為sum - 2n

*/