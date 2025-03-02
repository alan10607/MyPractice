//2D-DP O(n^2) O(1), n = matrix.size()
class Solution931 {
public:
    int minFallingPathSum(vector<vector<int>>& matrix) {
        int n = matrix.size();
        if (n == 1) return matrix[0][0]; // 代表是1x1直接返回

        int res = INT_MAX;
        for (int i = 1; i < n; ++i) { // 節省空間直接用matrix計算dp
            for (int j = 0; j < n; ++j) {
                int pre = matrix[i - 1][j];
                if (j - 1 >= 0) pre = min(pre, matrix[i - 1][j - 1]);
                if (j + 1 < n) pre = min(pre, matrix[i - 1][j + 1]);
                matrix[i][j] += pre;

                if (i == n - 1) res = min(res, matrix[i][j]); // 在最後一行時計算res
            }
        }

        return res;
    }
};