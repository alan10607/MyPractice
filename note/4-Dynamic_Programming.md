# Dynamic Programming
- https://leetcode.com/problems/fibonacci-number/
- https://leetcode.com/problems/coin-change/



DP三要素:
1. 狀態轉移方程式
2. 最優子結構 -> 透過子問題推導出答案
3. 重疊子問題 -> 透過DP table/memo的形式紀錄

1. 自頂向下
```cpp
vector<int> dp(0, ...); // dp table用來當memo紀錄

int solution(所有選擇, 狀態1, 狀態2, ...) {
    for (選擇 : 所有選擇) {
        // 依據選擇要更新狀態的值
        res = 求最值(res, dp(所有選擇, 新狀態1, 新狀態2, ...));
    }
    dp[狀態1] = res;
    return res;
}
```

2. 自底向上
```cpp
int solution(所有選擇, 狀態1, 狀態2, ...) {
    vector<int> dp(0, ...); // 依照題目要求初始化
    dp[0] = ...

    for(int i : 狀態1的所有可能) {
        for(int j : 狀態1的所有可能) {
        for... 
            for (選擇 : 所有選擇) {
                // 依據選擇要更新狀態的值
                dp[i][j][...] = 求最值(dp[i][j][...], 新狀態1, 新狀態2, ...);
            }
        }
    }
}
```
## Knapsack Problem
## Greedy