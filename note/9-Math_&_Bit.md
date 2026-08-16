# Math
- https://leetcode.com/problems/plus-one/
- https://leetcode.com/problems/powx-n/
- https://leetcode.com/problems/multiply-strings/
- https://leetcode.com/problems/detect-squares/

# Bit Manipulation
- https://leetcode.com/problems/single-number/
- https://leetcode.com/problems/number-of-1-bits/
- https://leetcode.com/problems/counting-bits/
- https://leetcode.com/problems/reverse-bits/
- https://leetcode.com/problems/missing-number/
- https://leetcode.com/problems/sum-of-two-integers/
- https://leetcode.com/problems/reverse-integer/

## 常見技巧
```cpp
n & 1
-> 判斷奇偶 / 取得最後一個 bit

n >> 1
n >>> 1 (Java)
-> 去掉最後一個 bit

n & (n - 1)
-> 消除最低位的 1
-> n > 0 且 == 0 -> n 是 2 的次方

(1 << k) - 1
-> Bit Mask: 取得 k 個 1

n & ((1 << k) - 1)
-> 取 n 的最後 k 個 bits

// Bit Packing, 將 x, y 放進同一個 int
int key = (x << 10) | (y & ((1 << 10) - 1));
int x = key >> 10; // 解碼 x
int y = key & ((1 << 10) - 1); // 解碼 y
```