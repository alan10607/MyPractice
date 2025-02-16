# Backtracking
- https://leetcode.com/problems/n-queens/
- https://leetcode.com/problems/n-queens-ii/
- https://leetcode.com/problems/surrounded-regions/
- https://leetcode.com/problems/number-of-enclaves/
- https://leetcode.com/problems/number-of-closed-islands/
- https://leetcode.com/problems/count-sub-islands/
- https://leetcode.com/problems/number-of-islands/
- https://leetcode.com/problems/max-area-of-island/


```cpp
auto res;

void backtracking(所有選擇, 已拜訪, 已選擇) {
    if (完成需要條件) {
        res.push_back(已選擇);
        return;
    }

    for (選擇 : 所有選擇) {
        if (該選擇已被拜訪) continue; 

        // 將該選擇加入已拜訪, 已選擇
        backtracking(nums, visited, selected);
        // 將該選擇移出已拜訪, 已選擇
    }
}
```

## DFS / BFS
### DFS
```cpp
void dfs(Node root) {
    if (!root) return;

    // 進行選擇
    for (Node child : root.children) {
        dfs(child);
    }
    // 撤銷選擇
}
```

### BFS
- https://leetcode.com/problems/open-the-lock/
```cpp
...
```


## Permutation / Subset / Combination
- https://leetcode.com/problems/subsets/
- https://leetcode.com/problems/combinations/
- https://leetcode.com/problems/permutations/
- https://leetcode.com/problems/subsets-ii/
- https://leetcode.com/problems/combination-sum-ii/
- https://leetcode.com/problems/permutations-ii/
- https://leetcode.com/problems/combination-sum/
- https://leetcode.com/problems/combination-sum-iii/
### 依據選項可以分成
1. 不重複, 不可複選
ex: nums=[2,3,6,7], target=7, res=[7]

2. 有重複, 不可複選
ex: nums=[2,3,2,5]

3. 不重複, 可複選
ex: nums=[2,3,6,7], target=7, res=[2,2,3],[7]


### 依據題型可以分成以下樹
A. Subset, Combination(子集, 組合)
- 子集數 2^n 個, 每個選項可以選擇選或不選
- 不考慮排序, 不同排序也視為同一種, ex:[1,2]=[2,1]
```cpp
                        []
        1               2               3
    12      13          23
    123
```

B. Permutation(排列)
- 子集數 n! 個, 每個選項可以選擇選或不選
- 考慮排序, 不同排序視為不同種, ex:[1,2]!=[2,1]
```cpp
                        []
        1               2               3
    12      13      21      23      31      32
    123     132     213     231     312     321
```

ex:  
- 1A: (不重複, 不可複選) + (Subset, Combination)
```cpp
void backtracking(起點, 已選擇, 所有選項) {
    if (完成需要條件) { // Subset時直接加入, Combinations依條件加入
        res.push_back(已選擇);
        return;
    }

    for (選項 : 自起點開始的所有選項) {
        // 選擇
        backtracking(起點 + 1, 已選擇, 所有選項);
        // 取消選擇
    }
}
```

- 1B: (不重複, 不可複選) + (Permutation)
```cpp
void backtracking(已拜訪, 已選擇, 所有選項) {
    if (完成需要條件) { // 通常是排序完時
        res.push_back(已選擇);
        return;
    }

    for (選項 : 所有選項) {
        if (該選項已被拜訪) continue; 

        // 選擇, 設為已拜訪
        backtracking(已拜訪, 已選擇, 所有選項);
        // 取消選擇, 設為已拜訪
    }
}
```

- 2A: (有重複, 不可複選) + (Subset, Combination)
```cpp
sort(所有選項.begin(), 所有選項.end()); // 若有重複, 就要透過排序處理

void backtracking(起點, 已選擇, 所有選項) {
    if (完成需要條件) {
        res.push_back(已選擇);
        return;
    }

    for (選項 : 自起點開始的所有選項) {
        if (選項index>起點 && 與上一個選項相同) continue;

        // 選擇
        backtracking(起點 + 1, 已選擇, 所有選項);
        // 取消選擇
    }
}
```

- 2B: (有重複, 不可複選) + (Permutation)
```cpp
sort(所有選項.begin(), 所有選項.end()); // 若有重複, 就要透過排序處理

void backtracking(已拜訪, 已選擇, 所有選項) {
    if (完成需要條件) {
        res.push_back(已選擇);
        return;
    }

    for (選項 : 所有選項) {
        if (該選項已被拜訪) continue; 

        // 跳過一樣的, 但只跳過不合順階規則的重複數字, ex: selected=[2], 遇到2''就跳過, 遇到2'可以繼續
        if (選項index>0 && 與上一個選項相同 && 前一個選項沒被選擇) continue;
        if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;


        // 選擇, 設為已拜訪
        backtracking(已拜訪, 已選擇, 所有選項);
        // 取消選擇, 設為已拜訪
    }
}
```

- 3A: (不重複, 可複選) + (Subset, Combination)
```cpp
void backtracking(起點, 已選擇, 所有選項) {
    if (完成需要條件) {
        res.push_back(已選擇);
        return;
    }

    for (選項 : 自起點開始的所有選項) {
        // 選擇
        backtracking(起點, 已選擇, 所有選項); // 從起點繼續試探(可複選所以可重複用)
        // 取消選擇
    }
}
```