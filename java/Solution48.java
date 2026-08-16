package leetCode.java;

//Matrix O(n^2) O(1)
class Solution48 {
    public void rotate(int[][] matrix) {
        // Must rotate the image in-place
        // 4-way swap
        int l = 0, r = matrix.length - 1;
        while (l < r) {
            int t = l, b = r;
            for (int i = 0; l + i < r; ++i) {
                int temp = matrix[t][l + i];
                matrix[t][l + i] = matrix[b - i][l];
                matrix[b - i][l] = matrix[b][r - i];
                matrix[b][r - i] = matrix[t + i][r];
                matrix[t + i][r] = temp;
            }
            ++l;
            --r;
        }
    }
}
/* 畫圖會更好理解
matrix[i][j], 則i代表從上到下, j代表從左到右
t/b是上下, l/r是左右, matrix[t/b, l/r]
一層一層往內縮

[t,l]       [t,r]


[b,l]       [b,r]



各方向分別代表:

            ---> [t][l+i]

                            |   
    ^       1   2   3       v
    |       4   5   6       [t+i][r]
[b-i][l]    7   8   9

            <--- [b][r-i]


*/


//Matrix O(n^2) O(1)
class Solution48_2 {
    public void rotate(int[][] matrix) {
        // Must rotate the image in-place
        // 1. Transpose
        int n = matrix.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) { // 從i+1開始, 因為對角線不用處理, 實際只要處理正方形對角的一半
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // 2. Reverse
        for (int i = 0; i < n; ++i) {
            int l = 0, r = n - 1;
            while (l < r) {
                int temp = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = temp;
                ++l;
                --r;
            }
        }
    }
}
/*
ex:
1   2   3
4   5   6
7   8   9

1. Transpose i,j互換

1   4   7
2   5   8
3   6   9

2. Reverse 每一行反轉

7   4   1
8   5   2
9   6   3

這樣相當於右轉90度, 
*/