//DP O(n) O(1)
class Solution746 {
public:
    int minCostClimbingStairs(vector<int>& cost) {
        int one = 0, two = 0;
        for(int i=2; i<=cost.size(); ++i){//要跨過floor, 所以算到邊界外
            int tmp = one;
            one = min(one + cost[i - 1], two + cost[i - 2]);
            two = tmp;
        }
        return one;
    }
};
/* cost = [1,100,1,1], dp[i] i 表示位置

dp[0]=0
dp[1]=0
dp[2]=dp[0]+1=1
dp[3]=dp[2]+1=2
...
dp[n]=min(dp[n-1]+cost[n-1], dp[n-2]+cost[n-2])

*/