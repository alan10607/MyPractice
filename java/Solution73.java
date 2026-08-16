package leetCode.java;

//Matrix O(mn) O(1)
class Solution73 {
    public void setZeroes(int[][] matrix) {
        // must do it in place, use O(1) space
        boolean firstRowZero = false;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    //處理 - 方向
                    if (i == 0) {
                        firstRowZero = true;
                    } else {
                        matrix[i][0] = 0;
                    }

                    //處理 | 方向
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; ++i) { // 先處理內層
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }
        if (firstRowZero == true) {
            for (int j= 0; j < n; ++j) {
                matrix[0][j] = 0;
            }
        }
    }
}
/*
使用第一行(matrix[i][0], matrix[0][j])列當作判斷器, 判斷該行列是否要歸零
那第一行列如何判斷是否歸零?
    - 第一col(matrix[i][0])可以用matrix[0][0]當判斷
    - 第一row(matrix[0][j])可以額外用一bool表示


  firstRowZero ->
    0   1   0   1
    |       |
    v       v
    1
    0 ->

*/