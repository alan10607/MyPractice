# Dynamic Programming
- https://leetcode.com/problems/fibonacci-number/
- https://neetcode.io/problems/climbing-stairs/
- https://leetcode.com/problems/min-cost-climbing-stairs/
- https://leetcode.com/problems/house-robber/
- https://leetcode.com/problems/house-robber-ii/
- https://leetcode.com/problems/decode-ways/


- DP三要素
1. 狀態轉移方程式
2. Optimal Substructure (最優子結構)
    - 大問題的最佳解，可以透過子問題推導出答案
3. Overlapping Subproblems (重疊子問題)
    - 相同子問題會被重複計算, 因此使用 memo 或 DP table 避免重算


- 如何辨認 DP?
通常題目會問:
    - Maximum / Minimum
    - Longest / Shortest
    - Count Ways
    - Can / Cannot
而且目前答案依賴之前的答案

- DP Transition: 
```cpp
// 最大 -> max(不選, 選)
dp[j] = max(dp[j], dp[j - weight] + value);

// 最小 -> min(不選, 選)
dp[j] = min(dp[j], dp[j - coin] + 1);

// 方法數 -> 繼承
dp[j] += dp[j - num];

// 是否可行 -> bool
dp[j] = dp[j] || dp[j - num];
```

- DP 四步驟每一題先回答四個問題:
1. State: DP 存什麼?
2. Transition: 目前答案怎麼由以前推導?
    - ex: dp[i] = max(dp[i-1], ...)
3. Base Case: 初始化
    - ex: dp[0] = 0
4. Answer: 答案在哪個dp[i]?


- Top-down vs Bottom-up
1. 自底向上, Bottom-up (Tabulation)
- 由最小的基礎Base Case, 往上推題目給的值, 一路推到答案
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

2. 自頂向下, Top-down (Memoization)
- 建立一個backtracking, 不斷分化成子問題
- DFS + Memo
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



## Knapsack Problem (背包問題)
- https://leetcode.com/problems/coin-change/
- https://leetcode.com/problems/coin-change-ii/
- https://leetcode.com/problems/partition-equal-subset-sum/
- https://leetcode.com/problems/target-sum/


背包問題: 在有限資源(capacity)下,  透過選擇物品(item)來求最佳解

1. 0/1 Knapsack 0/1 背包問題
- 每個 item 最多用一次
- capacity loop 要 倒序
```cpp
int knapsack01(vector<int>& weight, vector<int>& value, int capacity) {
    // weight=item重量, value=item價值, capacity=背包容量
    vector<int> dp(capacity + 1, 0);

    for (int i = 0; i < weight.size() ; ++i) {
        //  0/1 Knapsack: 容量要倒序, 因為每個 item 只能用一次, 而把 dp 壓成 1D 後,
        // 倒序是為了避免同一輪又使用到剛剛更新過的 dp
        for (int j = capacity; j >= weight[i]; --j) {
            dp[j] = max(dp[j], dp[j - weight[i]] + value[i]); // 選擇原本或是選擇 item[i]
        }
    }

    return dp[capacity];
}
```
ex:
weight = [1, 3, 4], value  = [15, 20, 30], capacity = 4
```cpp
                <------------- 逆序更新
            0   1   2   3   4
weight=1    0   15  15  15  15
weight=3    0   15  15  20  35
weight=4    0   15  15  20  35
```


2. Unbounded Knapsack 完全背包問題
- 允許 item 重複使用
- capacity loop 要 正序
- 硬幣問題:
    - 322 Coin Change 求「最少硬幣數」
        - dp[i] = 湊出 i 的最少硬幣
        - dp[i] = min(dp[i], dp[i-coin] + 1)
        - coin / amount loop 順序可交換
    - 518 Coin Change II 求「Combination 組合數量」
        - dp[i] = 湊出 i 的組合數
        - dp[i] += dp[i-coin]
        - coin 必須放外層
        - loop位置判斷:
            - 最值 -> loop 通常可交換
            - Combination組合 -> coin 外層, 避免內層渲染導致答案重複
            - Permutation排列 -> coin 內層, 要讓內層渲染使答案重複
```cpp
int unboundedKnapsack(vector<int>& weight, vector<int>& value, int capacity) {
    // weight=item重量, value=item價值, capacity=背包容量
    vector<int> dp(capacity + 1, 0);

    for (int i = 0; i < weight.size(); ++i) {
        // Unbounded: 容量正序, dp[j - weight[i]] 可以使用目前 item 已更新的結果,
        // 因此同一個 item 可以被重複使用
        for (int j = weight[i]; j <= capacity; ++j) {
            dp[j] = max(dp[j], dp[j - weight[i]] + value[i]); // 選擇原本或是選擇 item[i]
        }
    }

    return dp[capacity];
}
```
ex:
weight = [1, 3, 4], value  = [5, 20, 30], capacity = 4
```cpp
                -------------> 正序更新
            0   1   2   3   4
weight=1    0   5   10  15  20
weight=3    0   5   10  20  25
weight=4    0   5   10  20  30
```


3. Bounded Knapsack 分數背包問題
- 每種 item 的數量有限且固定, 第i種 item 最多只能使用 count[i] 次
- 介於 0/1 Knapsack 和 Unbounded Knapsack 之間
```cpp
int boundedKnapsack(vector<int>& weight, vector<int>& value, vector<int>& count, int capacity) {
    // weight=item重量, value=item價值, count=使用次數限制, capacity=背包容量
    vector<int> dp(capacity + 1, 0);

    for (int i = 0; i < weight.size(); ++i) {
        for (int j = capacity; j >= weight[i]; --j) {
            // k = 這次拿幾個 item[i]
            for (int k = 1; k <= count[i] && k * weight[i] <= j; ++k) {
                dp[j] = max(
                    dp[j],
                    dp[j - k * weight[i]] + k * value[i] // 拿了k個
                );
            }
        }
    }

    return dp[capacity];
}
```

4. Fractional Knapsack Problem
- item 可以被切割, 可以只拿 item 的一部分, 例如只拿 30% 的物品
- 不適合用一般 Knapsack DP, 可以使用 Greedy
- 按照: value / weight, 由高到低排序, 優先拿單位CP值最高的 item
```cpp
sort(items.begin(), items.end(),
     [](auto& a, auto& b) {  return a.value / a.weight > b.value / b.weight;});
```



## Longest Increasing Subsequence LIS
- https://leetcode.com/problems/longest-increasing-subsequence/
- https://leetcode.com/problems/russian-doll-envelopes/

### Patience Sorting 耐心排序法
LIS除了DP有個基於Patience Sorting 耐心排序的解法, 這是一個用來排序撲克牌的方法:

只能將較小的牌放在該堆上, 如果當前牌比所有堆都大, 則創建一個新堆放入該牌  
如果有多個堆可以放置當前牌, 則優先選擇最左邊的堆(lower_bound)  
由左到右看每堆的top, 都會是小到大排序  
ex:  
```cpp
num = 5,2,4,9,10,1,8,13,12,6,3,7,11

5   4   9   10  13
2   3   8   7   12
1       6       11

此時堆的數量就是LIS, 此例LIS=5
```
```cpp
int lengthOfLIS(vector<int>& nums) {
    vector<int> piles; // 每堆最上面的牌
    for (int num : nums) {
        auto it = lower_bound(piles.begin(), piles.end(), num); // 尋找piles中的左側邊界(第一個比num大的)
        if (it == piles.end()) {
            piles.push_back(num); // 如果都比這些堆最上面的牌大, 則再建立一堆
        } else {
            *it = num; // 蓋牌到該堆最上面
        }
    }
    return piles.size();
}
```


## String DP
- https://leetcode.com/problems/word-break/
- https://leetcode.com/problems/word-break-ii/



## 2D DP
- https://leetcode.com/problems/unique-paths/
- https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
- https://leetcode.com/problems/interleaving-string/
- https://leetcode.com/problems/burst-balloons/
- https://leetcode.com/problems/minimum-falling-path-sum/


## Sequence DP / Subsequences 子序列
- https://leetcode.com/problems/longest-common-subsequence/
- https://leetcode.com/problems/distinct-subsequences/
- https://leetcode.com/problems/edit-distance/


### 相似的部分
- 定義DP: 有兩個字串分別是s, t
dp[i][j]通常代表 s的前i個字元 配對 t的前j個字元, 所能得到的最佳解
所以通常判斷s[i - 1], t[j - 1], dp size 為[m+1][n+1]

- 轉移方程: 判斷字元相不相等
s[i-1] == t[j-1]考慮使用這個字串
s[i-1] != t[j-1]不使用這個字元的情況下


### Sequence 題型
1. LCS (Longest Common Subsequence)
```cpp
預設值:
dp[i][0] = 0; //其中一個text長度為0時候, LCS也會是0
dp[0][j] = 0;

轉移方程:
if (s1[i - 1] == s2[j - 1]) {
    dp[i][j] = dp[i - 1][j - 1] + 1; // 字母相同使用這個字元, LCS+1
} else {
    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]); // 字母不同則應該繼承較多可能的LCS
}
```
```cpp
ex: text1 = "abcde", text2 = "ace" 

    _   a   c   e
_   0   0   0   0
a   0   1   1   1
b   0   1   1   1
c   0   1   2   2
d   0   1   2   2
e   0   1   2   3
```

2. Distinct Subsequences
```cpp
預設值:
dp[i][0] = 1; // 空字串s2可以由任意字串s1組出

轉移方程:
if (s1[i - 1] == s2[j - 1]) {
    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1]; // 除了繼承外, 因為字元相同可以加上[i-1][j-1]的條件擁有的
} else {
    dp[i][j] = dp[i - 1][j]; // 繼承s在i-1的可能
}
```
```cpp
    _   b   a   g
_   1   0   0   0
b   1   1   0   0
a   1   1   1   0
b   1   2   1   0
g   1   2   1   1
b   1   3   1   1
a   1   3   4   1
g   1   3   4   5
```

3. Edit Distance
```cpp
預設值:
dp[i][0] = 1; // 空字串s2可以由任意字串s1組出

轉移方程:
if (s1[i - 1] == s2[j - 1]) {
    dp[i][j] = dp[i - 1][j - 1]; // 字元相同就不用修改, 直接使用[i-1][j-1]
} else {
    // 選擇 min(新,刪,改)中最小的+1, 其中
    // dp[i-1][j]+1代表刪除s1
    // dp[i][j+1]+1代表新增s2
    // dp[i-1][j-1]+1代表修改s1某字母到s2
    dp[i][j] = min(dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]) + 1;
}
```
```cpp
    _   r   o   s
_   0   1   2   3
h   1   1   2   3
o   2   2   1   2
r   3   2   2   2
s   4   3   3   2
e   5   4   4   3
```



## Stock DP (股票問題)
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/


dp[i][k][0 or 1]定義成:
i = 第i+1天(index+1), 0 <= i <= 總交易天數 - 1
k = 最多可以完成幾次交易, 0 <= k <= 總交易數
0 = 今天結束時手上沒有股票
1 = 今天結束時手上有股票
所以dp[i][k][0]代表到第 i 天結束, 最多交易 k 次, 而且手上 沒有股票時的最大收益
dp[i][k][1]代表到第 i 天結束, 最多交易 k 次, 而且手上 有 股票時的最大收益

prices[i] 表第i天的股價

則轉移方程式:  
- dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])  
    不持有股票 = max(不持有然後不做事, 持有股票今天賣出)

- dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])  
    持有股票 = max(持有然後不做事, 不持有股票今天買入)
    我要進行第 k 次的買入, 所以我的前置狀態必須是「已完成 k-1 次交易, 且手上沒股票 (0)」的狀態

```cpp
int maxProfit(int max_k, vector<int>& prices) {
    int n = prices.size();
    // <交易日期-1, 剩餘交易次數, 是否持有股票>
    vector<vector<vector<int>>> dp(n, vector<vector<int>>(max_k + 1, vector<int>(2)));

    for (int i = 0; i < n; ++i) {
        for (int k = max_k; k > 0; --k) {
            if (i == 0) { // base case
                dp[i][k][0] = 0; // 第一天不做事
                dp[i][k][1] = -prices[i]; // 第一天買入
                continue;
            }
            dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]); // max(不做事, 股票賣出)
            dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]); // max(不做事, 股票買入)
        }
    }

    return dp[n - 1][max_k][0]; // 取最大交易次數, 交易次數多的利潤會>=交易次數較少的
}
```

## Greedy