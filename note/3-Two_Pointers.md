# Two Pointers

## Fast-Slow pointers
- https://leetcode.com/problems/happy-number/
- https://leetcode.com/problems/remove-duplicates-from-sorted-array/
- https://leetcode.com/problems/remove-element/
- https://leetcode.com/problems/move-zeroes/


### In-Place Modification
```cpp
void fastSlowPointer(vector<int>& nums) {
    int fast = 0, slow = 0;
    while (fast < nums.size()) {
        ...
    }
```


## L-R Pointers
- https://leetcode.com/problems/container-with-most-water/
- https://leetcode.com/problems/trapping-rain-water/
- https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
- https://leetcode.com/problems/reverse-string/
- https://leetcode.com/problems/advantage-shuffle/

### Binary Search
- https://leetcode.com/problems/binary-search/
- https://leetcode.com/problems/search-a-2d-matrix/
- https://leetcode.com/problems/koko-eating-bananas/
- https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
- https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
- https://leetcode.com/problems/time-based-key-value-store/
- https://leetcode.com/problems/median-of-two-sorted-arrays/
- https://leetcode.com/problems/split-array-largest-sum/
- https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
- https://leetcode.com/problems/count-of-smaller-numbers-after-self/


- 關於lower, upper bound
    - lower_bound(下界): 找第一個 >= target 的位置 / 找第一個不小於某目標的數
    - upper_bound(上界): 找第一個 > target 的位置 / 找第一個大於某目標的數

- 關於 floor, ceiling
    - floor(地板): 找最大 <= target 的數
        - max(x <= target)
        - index 對應: upper_bound - 1

    - ceiling(天花板): 找最小 >= target 的數
        - min(x >= target)
        - index 對應: lower_bound


    - ex:[0,1,1,1,1], target=1
        - lower_bound, upper_bound = [1,5)
        - floor(value), ceiling(value) = [1,1]
        - floor(index), ceiling(index) = [4,1]


    - ex:[0,1,3,4], target=2
        - lower_bound, upper_bound = [2,2)
        - floor(value), ceiling(value) = [1,3]
        - floor(index), ceiling(index) = [1,2]


    - ex:[0,1,3,4], target=5
        - lower_bound, upper_bound = [4,4)
        - floor(value), ceiling(value) = [4,null]
        - floor(index), ceiling(index) = [3,null]


    - ex:[0,1,3,4], target=2
        - lower_bound = 2 (value=3)
        - upper_bound = 2 (value=3)
        - floor = 1
        - ceiling = 3


    - ex:[0,1,3,4], target=5
        - lower_bound = 4 (不存在)
        - upper_bound = 4 (不存在)
        - floor = 4
        - ceiling = 不存在

- 關於while 
    - 找目標值: 通常 while (l <= r)
    - 找極值, 最大, 最小值: 通常 while (l < r)

- 關於mid
    - mid = l + (r - l) / 2;
    - 原因: 避免 (l + r) 相加 overflow



1. 找某目標index, 不存在則返回-1
    - 搜索區間是 [l, r]
    - mid不為target時, 應該尋找[l, mid - 1]或[mid + 1, r]
    - 因為是精確搜尋, 所以mid沒命中時, 要排除mid本身, 沒命中時: l = mid + 1或r = mid - 1
```cpp
int binarySearch(vector<int>& nums, int target) {
    int l = 0, r = nums.size() - 1;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            l = mid + 1;
        } else if (nums[mid] > target) {
            r = mid - 1;
        }
    }
    return -1;
}
```

2. lower_bound, 左側邊界, 找第一個不小於某目標的數, 第一個 >= target的數
    - 搜索區間是 [l, r)
    - 當 nums[mid] >= target 時:
        mid 可能是答案, 不能丟棄, 所以 r = mid
    - 當 nums[mid] < target 時:
        mid 一定不是答案, 所以 l = mid + 1
    - 記憶方法: 往左慢慢逼近
```cpp
int lowerBound(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] >= target) {
            r = mid;
        } else { // nums[mid] < target
            l = mid + 1;
        }
    }
    return l; // 或r也可以, 因為l == r
}
```

3. upper_bound, 右側邊界, 找第一個大於某目標的數, 第一個 > target的數
    - 搜索區間是 [l, r)
    - 當 nums[mid] <= target 時:
        mid 一定不是答案, 所以 l = mid + 1
    - 當 nums[mid] > target 時:
        mid 可能是答案, 不能丟棄, 所以 r = mid
    - 記憶方法: 往右快速逼近
```cpp
int upperBound(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] <= target) {
            l = mid + 1;
        } else { // nums[mid] > target
            r = mid;
        }
    }
    return l; // 或r也可以, 因為l == r
}
```


## Sliding Window
- https://leetcode.com/problems/longest-substring-without-repeating-characters/
- https://leetcode.com/problems/longest-repeating-character-replacemen/
- https://leetcode.com/problems/permutation-in-string/
- https://leetcode.com/problems/minimum-window-substring/
- https://leetcode.com/problems/sliding-window-maximum/
- https://leetcode.com/problems/find-all-anagrams-in-a-string/
- https://leetcode.com/problems/reverse-words-in-a-string/
- https://leetcode.com/problems/repeated-dna-sequences/
```cpp
string slidingWindow(string s) {
    auto window;
    int l = 0, r = 0;
    while (r < s.length()) { // 以下都是左閉右開[l, r) 方便計算
        window.add(s[r]); // 加入目前這一個
        ++r; // 先移動到下一個

        while (滿足某條件情況下, 開始收縮左邊) {
            // 依照題目要求進行處理
            int len = r - l; // 左閉右開[l, r)

            window.remove(s[l]); // 移除最左邊這個  
            ++l; // 繼續看下一個左邊
        }
    }
}
```


### Palindrome 迴文
- https://leetcode.com/problems/valid-palindrome/
- https://leetcode.com/problems/longest-palindromic-substring/
- https://leetcode.com/problems/palindromic-substrings/
```cpp
string palindrome(int l, int r, string s) {
    while (0 <= l && r < s.length() && s[l] == s[r]) {
        --l;
        ++r;
    }
    ++l; // 抵銷最後一次
    --r; // 抵銷最後一次
    return s.substr(l, r - l + 1);
}
```

#### Manacher's Algorithm 拉馬車算法
```cpp
string longestPalindrome(string s) {
    // 1. 插入#與頭尾 ex: "abba" -> "^#a#b#b#a#$"
    string t = "^";
    for (char c : s) {
        t += "#";
        t += c;
    }
    t += "#$";

    // 2. Manacher, find p[]
    vector<int> p(t.size(), 0); // p[i]代表t[i]可以向左右展開多少, 或說是s[i]為中心的迴文長度
    int c = 0, r = 0; // c=展開中心, r=展開最右界(包含)
    int maxLen, maxCenter = 0;
    for (int i = 1; i < n - 1; i++) { // 頭尾不判斷
        if (i < r) { // 在範圍內可以透過鏡像快速找到可行的下限
            int mirror = 2 * c - i; // = c - ( i - c)
            p[i] = min(r - i, p[mirror]);
        }

        while (t[i + p[i] + 1] == t[i - p[i] - 1]) { // 展開
            p[i]++;
        }

        if (i + p[i] > r) { // 更新最靠右的邊界
            c = i;
            r = i + p[i];
        }

        if (p[i] > maxLen) { // 記錄最長迴文
            maxLen = p[i];
            maxCenter = i;
        }
    }

    // 3. 轉回原始字串
    int start = (maxCenter - maxLen) / 2;
    return s.substr(start, maxLen);
}
```
ex:
s = "bcbabcc"

1. 插入#與頭尾
t = "^#b#c#b#a#b#c#c#$"
此時不管從哪個字符看, 左右擴展長度=回文串長度
ex1: #b# -> b為中心左右擴展1, 回文長度1(b)
ex2: #b#c#b# -> c為中心左右擴展3, 回文長度3(bcb)
ex3: #c#c# -> #為中心左右擴展2, 回文長度2(cc)


2. 透過對稱性, 可以鏡像判斷回文長度
可以想成回文都是一朵朵香菇
P[i]表對於t[i]左右可以擴展多少
C表示蘑菇中心位置
R表示蘑菇右側邊界
```cpp

                _________________________________________
        _________________________   |           _________
        _________   |               |           ____|____________
            |       |               |               |   |________
    _   _   |   _   |               |               |   |   |
    ^   #   b   #   c   #   b   #   a   #   b   #   c   #   c   #   $
P[] 0   0   1   0   3   0   1   0   5   0   1   0   1   0   1   0   0
                ^       ^-------^       ^-------^   ^   ^   ^---^
                        透過鏡像得到      透過鏡像得到  |   |
                                                    |   |
                                                    c的鏡像香菇超過a大菇的範圍,不能照抄
                                                    只能假設他最大可以碰到R
                                                    i距離R=R-i
                                                    距離中心i-C, 鏡像位置是 C-(i-C)=2*C-i
                                                    P[i]=min(R-i, P[2*C-i])

                                                    #也是同理
```

3. 求出所有P[]後, 最長回文字串=P[]中的最大值


### N-Sum
- https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
- https://leetcode.com/problems/3sum/
- https://leetcode.com/problems/4sum/

1. 要先排序
2. 重複的數字可以跳過
3. 注意左右指標的判斷, 有時候找到值也要繼續移動
```cpp
vector<vector<int>> threeSum(vector<int>& nums) {
    sort(nums.begin(), nums.end()); // c++要指定範圍

    vector<vector<int>> res;
    for (int i = 0; i < nums.size() - 2; ++i) {
        if (i > 0 && nums[i] == nums[i - 1]) { // 跳過重複的
            continue;
        }
        
        int l = i + 1;
        int r = nums.size() - 1;
        while (l < r) {
            if (l > i + 1 && nums[l] == nums[l - 1]) { // 跳過重複的
                ++l;
                continue;
            }
            if (r < nums.size() - 1 && nums[r] == nums[r + 1]) { // 跳過重複的
                --r;
                continue;
            }

            int sum = nums[i] + nums[l] + nums[r];
            if (sum == 0) res.push_back({nums[i], nums[l], nums[r]});

            if (sum < 0) {
                ++l;
            } else { // sum > 0 || sum == 0 時都要移動
                --r;
            }
        }
    }
    return res;
}
```


### KMP (Knuth-Morris-Pratt)
- https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/

用來判斷substring index的經典算法
```cpp
int findFirstIndexOf(string s, string p) {
    vector<int> lps = buildLps(p);
    int j = 0;
    for (int i = 0; i < s.size(); ++i) {
        while (j > 0 && s[i] != p[j]) {
            j = lps[j - 1]; // 與buildLps一樣如果不匹配可以回到前一個相同前綴就好
        }

        if (s[i] == p[j]) {
            ++j;
        }

        if (j == p.size()) {
            return (i + 1) - p.size(); // i還在這個字母上, 先+1到下一個
        }
    }
    return -1;
}

vector<int> buildLps(string s) {
    vector<int> lps(s.length(), 0);
    int i = 1;
    int j = 0;
    for (int i = 1; i < s.length(); ++i) {
        while (j > 0 && s[i] != s[j]) {
            j = lps[j - 1]; // 退回之前有讀過的長度
        }

        if (s[i] == s[j]) {
            ++j; // 直接繼續找下一個
        }

        lps[i] = j;
    }
    return lps;
}
```
```cpp
如何建立 LPS(Longest Prefix Suffix) ?
這個表計算前綴與後綴相同的長度有多少
ex: abacabab

a,                  lps[0]=0, 必須跳過自己, 所以自己不能用, 為0
ab,       j=0, i=1, lps[1]=0
aba,      j=0, i=2, lps[2]=1, prefix=a
abac,     j=1, i=3, 不匹配, j退回lps[1]=0
abac,     j=0, i=3, 還是不匹配, lps[3]=0
abaca,    j=0, i=4, lps[4]=1, prefix=a
abacab,   j=1, i=5, lps[5]=2, prefix=ab
abacaba,  j=2, i=6, lps[6]=3, prefix=aba
abacabab, j=3, i=7, 不匹配, j退回lps[2]=1
abacabab, j=1, i=7, 匹配了, lps[7]=2, prefix=ab

lps={0,0,1,0,1,2,3,2}
```