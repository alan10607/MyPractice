//Bit + DP O(n) O(1)
class Solution338 {
public:
    vector<int> countBits(int n) {
        vector<int> dp(n + 1);
        int base = 0;
        for(int i=1; i<=n; ++i){
            if((i & (i - 1)) == 0)
                base = i;

            dp[i] = 1 + dp[i - base];
        }
        return dp;
    }
};
/*
dp[0]=0
dp[1]=1
dp[2]=1+dp[0]=10
dp[3]=1+dp[1]=11
dp[4]=1+dp[0]=100
...

*/