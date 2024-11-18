//DP NumArray(): O(mn) O(mn), sumRange(): O(1) O(mn)
class NumMatrix {//Solution304
public:
    vector<vector<int>> dp;
    NumMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size();
        dp.resize(m + 1, vector<int>(n + 1, 0));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] - dp[i][j] + matrix[i][j];
            }
        }
    }

    int sumRegion(int x1, int y1, int x2, int y2) {
        return dp[x2 + 1][y2 + 1] - dp[x1][y2 + 1] - dp[x2 + 1][y1] + dp[x1][y1];
    }
};

/* 類似Solution303, 由上而下由左而右累加
+-------+-----+-----+
|   1   |  3  |     |
+-------+-----+-----+
|   2   |  4  |     |
|       |     |     |
+-------+-----+-----+
|       |     |     |
+-------+-----+-----+

A = 1+2+3+4
B = 1+3
C = 1+2
D = 1
E = 4

若所求為E, 則大小為A-B-C+D, 下面看看範例

ex:

        0   1   2

0       1   2   3
1       4   5   6
2       7   8   9


dp:
        0   1   2   3

0       0   0   0   0
1       0   1   3   6
2       0   5   12  22
3       0   12  27  46

dp[2][2] = dp[1][2] + dp[2][1] - dp[1][1] + matrix[1][1]

反知
matrix[1][1] = dp[2][2] - dp[1][2] - dp[2][1] + dp[1][1]

*/