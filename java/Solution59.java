package leetCode.java;

//Matrix O(mn) O(1)
class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int num = 1;
        while (true) {
            for (int i = 0; l + i <= r; ++i) {
                matrix[t][l + i] = num++;
            }
            if (++t > b) {
                break;
            }

            for (int i = 0; t + i <= b; ++i) {
                matrix[t + i][r] = num++;
            }
            if(l > --r) {
                break;
            }

            for (int i = 0; r - i >= l; ++i) {
                matrix[b][r - i] = num++;
            }
            if (t > --b) {
                break;
            }

            for (int i = 0; b - i >= t; ++i) {
                matrix[b - i][l] = num++;
            }
            if (++l > r) {
                break;
            }
        }
        return matrix;
    }
}