# Two Pointers

## Fast-Slow pointers
- https://leetcode.com/problems/remove-duplicates-from-sorted-array/
- https://leetcode.com/problems/remove-element/
- https://leetcode.com/problems/move-zeroes/


### In-Place Modification
```
void fastSlowPointer(vector<int>& nums) {
    int fast = 0, slow = 0;
    while (fast < nums.size()) {
        ...
    }
```


### Sliding Window
- https://leetcode.com/problems/minimum-window-substring/
- https://leetcode.com/problems/permutation-in-string/
- https://leetcode.com/problems/find-all-anagrams-in-a-string/
- https://leetcode.com/problems/longest-substring-without-repeating-characters/
- https://leetcode.com/problems/reverse-words-in-a-string/
- https://leetcode.com/problems/repeated-dna-sequences/
```
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


## L-R Pointers
- https://leetcode.com/problems/reverse-string/

### Binary Search
- https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
- https://leetcode.com/problems/binary-search/
- https://leetcode.com/problems/koko-eating-bananas/
- https://leetcode.com/problems/split-array-largest-sum/
- https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/


1. 找某目標index, 不存在則返回-1  
    - 搜索區間是[l, r], mid不為target時, 應該尋找[l, mid - 1]或[mid + 1, r]
```
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

2. lower_bound, 左側邊界, 找第一個不小於某目標的數
    - ex:[0,1,1,1,1], target=1, return=1  
    - 搜索區間是[l, r), mid不為target時, 應該尋找[l, mid)或[mid + 1, r)
    - 若相同, 則應該往左側尋找
```
int lowerBound(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] == target) {
            r = mid
        } else if (nums[mid] < target) {
            l = mid + 1;
        } else if (nums[mid] > target) {
            r = mid;
        }
    }
    return l; // 或r也可以, 因為l == r
}
```

3. upper_bound, 右側邊界, 找第一個大於某目標的數
    - ex:[0,1,1,1,1], target=1, return=5  
    - 搜索區間是[l, r), mid不為target時, 應該尋找[l, mid)或[mid + 1, r)
    - 若相同, 則應該往右側尋找
```
int upperBound(vector<int>& nums, int target) {
    int l = 0, r = nums.size();
    while (l < r) {
        if (nums[mid] == target) {
            l = mid + 1
        } else if (nums[mid] < target) {
            l = mid + 1;
        } else if (nums[mid] > target) {
            r = mid;
        }
    }
    return l; // 或r也可以, 因為l == r
}
```


### Palindrome 迴文
- https://leetcode.com/problems/longest-palindromic-substring/
```
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
### N-Sum
- https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
- https://leetcode.com/problems/3sum/
- https://leetcode.com/problems/4sum/

1. 要先排序
2. 重複的數字可以跳過
3. 注意左右指標的判斷, 有時候找到值也要繼續移動
```
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
```
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
```
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