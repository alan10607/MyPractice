package leetCode.java;

import java.util.*;

//NumArray(): O(mn) O(mn), sumRange(): O(1) O(mn)
class NumMatrix {//Solution304
    int[][] sums;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        this.sums = new int[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + matrix[i][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sums[row2 + 1][col2 + 1] - sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
    }
}
/* 類似Solution303, 由上而下由左而右累加
+-------+-----+-----+
|       |     |     |
+-------+-----+-----+
|       |  1  |  3  |
|       |     |     |
+-------+-----+-----+
|       |  2  |  4  |
+-------+-----+-----+

A = 1+2+3+4
B = 1+3
C = 1+2
D = 1
E = 4

若所求為E, 則大小為A-B-C+D

在加總的時候,
假設要計算4的sum, = B+C-D+4
*/