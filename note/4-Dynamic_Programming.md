# Dynamic Programming
- https://leetcode.com/problems/fibonacci-number/
- https://leetcode.com/problems/coin-change/

## Longest Increasing Subsequence LIS
- https://leetcode.com/problems/longest-increasing-subsequence/
- https://leetcode.com/problems/russian-doll-envelopes/

## 2D DP
- https://leetcode.com/problems/minimum-falling-path-sum/

## Subsequences 子序列
- https://leetcode.com/problems/distinct-subsequences/

- https://leetcode.com/problems/word-break/
- https://leetcode.com/problems/word-break-ii/

## 股票問題
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/


```cpp
int maxProfit(int max_k, vector<int>& prices) {
    int n = prices.size();
    // <交易日期-1, 剩餘交易次數, 是否持有股票>
    vector<vector<vector<int>>> dp(n, vector<vector<int>>(max_k + 1, vector<int>(2)));

    for (int i = 0; i < n; ++i) {
        for (int k = max_k; k > 0; --k) {
            if (i == 0) { // base case
                dp[i][k][0] = 0;
                dp[i][k][1] = -prices[i];
                continue;
            }
            dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
            dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
        }
    }

    return dp[n - 1][max_k][0]; //取最大交易次數, 交易次數多的利潤會>=交易次數較少的
}
```
prices[i] 表第i天的股價  
dp[i][k][0 or 1] 表最大收益, 其中  
- i表示第 i+1 天做的交易, 0 <= i <= 總交易天數 - 1
- k表示剩餘的交易次數,  0 <= k <= 總交易數
- 0 or 1代表是否持有股票

則轉移方程式:  
-   dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])  
    不持有股票 = max(不持有然後不做事, 持有股票今天賣出)

-   dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])  
    持有股票 = max(持有然後不做事, 不持有股票今天買入), 要買入代表進行交易, 所以這裡要用k-1代表前一次交易的狀態


## DP三要素
1. 狀態轉移方程式
2. 最優子結構 -> 透過子問題推導出答案
3. 重疊子問題 -> 透過DP table/memo的形式紀錄


## 自底向上
- 由最小的基礎, 往上推題目給的值

```cpp
int solution(所有選擇, 狀態1, 狀態2, ...) {
    vector dp;
    dp[0] = ... // 依照題目要求初始化base case

    for(int i : 狀態1的所有可能) {
        for(int j : 狀態2的所有可能) {
        for... 
            for (選擇 : 所有選擇) {
                // 依據選擇要更新狀態的值
                dp[i][j][...] = 求最值(dp[i][j][...], 新狀態1, 新狀態2, ...);
            }
        }
    }
    return dp[...] // 依照題目回應頂端值
}
```

## 自頂向下
- 建立一個backtracking, 不斷分化成子問題

```cpp
vector dp; // dp table用來當memo紀錄
dp[0] = ... // 依照題目要求初始化base case

int backtracking(所有選擇, 狀態1, 狀態2, ...) {
    if(dp[[狀態1][狀態2]]已被計算) return dp[[狀態1][狀態2]];

    auto res = ...
    for (選擇 : 所有選擇) {
        // 依據選擇要更新狀態的值
        res = 求最值(res, backtracking(所有選擇, 新狀態1, 新狀態2, ...));
    }
    dp[狀態1][狀態2][...] = res; // 跑完children的所有可能後更新該dp node
    return res;
}
```

## Knapsack Problem
## Greedy