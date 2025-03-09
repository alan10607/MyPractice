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