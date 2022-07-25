package leetCode.java;

//DP O(n) O(n)
class Solution91 {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for(int i=1; i<=s.length(); i++){
            //1位數, 0 ~ 9
            if(s.charAt(i - 1) >= '1' && s.charAt(i - 1) <= '9')
                dp[i] += dp[i - 1];

            //2位數, 10 ~ 26
            if(i > 1 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')))
                dp[i] += dp[i - 2];
        }
        return dp[s.length()];
    }
}
/*
s = "226"
dp[0] = 1
dp[1] = (2) = 1
dp[2] = (2 2)(22) = dp[1] + dp[0] = 2
dp[3] = (2 2 6)(22 6)(2 26) = dp[2] + dp[1] = 3
...
*/