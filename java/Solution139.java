package leetCode.java;

import java.util.*;

//DP O(n^2) O(n), n = s.length()
class Solution139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for(int i=1; i<=s.length(); i++){
            //往後找是否有可能
            for(int j = i - 1; j>=0; j--){
                //dp[j]為true, 才改為true並且break
                if(dp[j] && words.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
/*
"leetcode", ["leet", "code"]
dp[0] = true, 空字串也符合
dp[1] = (dp[0] && s.substring(0, 1)) = false
dp[2] = (dp[0] && s.substring(0, 2)) || (dp[1] && s.substring(1, 2)) = false
dp[3] = (dp[0] && s.substring(0, 3)) || (dp[1] && s.substring(1, 3))
     || (dp[2] && s.substring(2, 3)) = false
dp[4] = (dp[0] && s.substring(0, 4)) || (dp[1] && s.substring(1, 4))
     || (dp[2] && s.substring(2, 4)) || (dp[3] && s.substring(3, 4)) = true
...
dp[n] = (dp[0] && s.substring(0, n)) || ... (dp[n - 1] && s.substring(n - 1, n))
*/