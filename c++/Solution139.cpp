//DP O(mn) O(n), n = s.length(), m = wordDict.size();
class Solution139 {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        //透過DP減字查驗
        vector<bool> dp(s.length() + 1);
        dp[0] = true;
        for(int i=1; i<= s.length(); ++i){
            for(auto word : wordDict){//另一種解法是把wordDict變成set, 然後讀取i之前的字串查有無存在
                int len = word.length();
                if(i >= len && dp[i - len] && s.substr(i - len, len) == word){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
};