//DP O(n) O(1)
class Solution509 { // 自底向上
public:
    int fib(int n) {
        if (n < 2) return n;
        
        int first = 1, second = 0;
        for (int i = 2; i <= n; ++i) {
            int tmp = first;
            first = first + second;
            second = tmp;
        }
        return first;
    }
};


//DP O(n) O(n)
class Solution509_2 { //自頂向下
public:
    vector<int> dp;
    int fib(int n) {
        if (n < 2) return n;
        
        dp = vector<int>(n + 1, -1);
        dp[0] = 0;
        dp[1] = 1;
        return backtracking(n);
    }

    int backtracking(int n) {
        if (dp[n] != -1) return dp[n];

        dp[n] = backtracking(n - 1) + backtracking(n - 2);
        return dp[n];
    }
};