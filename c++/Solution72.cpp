//2D-DP O(mn) O(mn)
class Solution72 {
public:
    int minDistance(string word1, string word2) {
        int m = word1.length(), n = word2.length();
        vector<vector<int>> dp(m + 1, vector<int>(n + 1));
        for(int i=0; i<=m; ++i) dp[i][0] = i;
        for(int j=0; j<=n; ++j) dp[0][j] = j;

        for(int i=1; i<=m; ++i){
            for(int j=1; j<=n; ++j){
                if(word1[i - 1] == word2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{//增刪改, 都要一個步驟
                    dp[i][j] = min(min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }
};
/* word1 = "horse", word2 = "ros"

        r   o   s
    0   1   2   3
h   1   1   2   3
o   2   2   1   2
r   3   2   2   2
s   4   3   3   2
e   5   4   4   3

*/