//Matrix O(mn) O(1)
class Solution59 {
public:
    vector<vector<int>> generateMatrix(int n) {
        vector<vector<int>> res(n, vector<int>(n));
        int t = 0, b = n - 1, l = 0, r = n - 1;
        int num = 1;
        while (true) { // Solution54的概念, 大同小異
            for (int i = 0; l + i <= r; ++i)
                res[t][l + i] = num++;

            if (++t > b) break;

            for (int i = 0; t + i <= b; ++i)
                res[t + i][r] = num++;

            if (l > --r) break;

            for (int i = 0; r - i >= l; ++i)
                res[b][r - i] = num++;

            if (t > --b) break;

            for (int i = 0; b - i >= t; ++i)
                res[b - i][l] = num++;

            if (++l > r) break;
        }
        return res;
    }
};