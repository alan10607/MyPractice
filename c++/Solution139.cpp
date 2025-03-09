//DP O(mn) O(n), n = s.length(), m = word_dict.size()
class Solution139 {
public:
    bool wordBreak(string s, vector<string>& word_dict) {
        int n = s.length();
        vector<bool> dp(n + 1); // dp[i]表示是否可組出s.substr(0,i)
        dp[0] = true; // 空字串必可組出
        
        for (int i = 1; i <= n; ++i) {
            for (string word : word_dict) {
                int len = word.length();
                if (i >= len && dp[i - len] && word == s.substr(i - len, len)) {
                    dp[i] = true;
                }
            }
        }
        
        return dp[n];
    }
};