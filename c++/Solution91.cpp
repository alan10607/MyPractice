//DP O(n) O(n)
class Solution91 {
public:
    int numDecodings(string s) {
        vector<int> dp(s.length() + 1);
        dp[0] = 1;
        for(int i=1; i<dp.size(); ++i){
            if(s[i - 1] >= '1' && s[i - 1] <= '9')
                dp[i] += dp[i - 1];

            if(i >= 2 && (s[i - 2] == '1'
                      || (s[i - 2] == '2' && (s[i - 1] >= '0' && s[i - 1] <= '6'))))
                dp[i] += dp[i - 2];
        }
        return dp.back();
    }
};
/* s = "226"
dp[0]=1
dp[1]=(2)=1
dp[2]=(2 2, 22)=2
dp[3]=(2 2 6, 226, 2 26)=3
*/