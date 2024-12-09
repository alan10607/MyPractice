//DFS(BST) Catalan Number O((4^n)/(n^(1/2))) O((4^n)/(n^(1/2)))
class Solution96 {
public:
    int numTrees(int n) {
        vector<vector<int>> memo(n + 1, vector<int>(n + 1)); // count(start, end)的結果
        return count(1, n, memo);
    }

    int count(int start, int end, vector<vector<int>>& memo) {
        if (start > end) return 1; // nullptr視為一種
        if (memo[start][end] != 0) return memo[start][end]; // 透過memo剪枝, 否則會TLE
        
        int cnt = 0;
        for (int i = start; i <= end; ++i) {
            int left_cnt = count(start, i - 1, memo);
            int right_cnt = count(i + 1, end, memo);
            cnt += left_cnt * right_cnt; // 左邊的所有可能 * 右邊的所有可能
        }
        memo[start][end] = cnt;
        return cnt;
    }
};


class Solution96_2 {
public:
    int numTrees(int n) {
        vector<int> dp(n + 1);
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                dp[j] += dp[j - 1]; // 上面+左邊
            }
        }
        return dp[n];
    }
};
/* BST的變化是Catalan 卡塔蘭數: 1, 1, 2, 5, 14, 42, 132, ...
最右側的數是左+上得到的

index   0   1   2   3   4   5

        1
        1   1
        1   2   2
        1   3   5   5
        1   4   9   14  14
        1   5   14  28  42  42
...

n=0, res=1
    []

n=1, res=1
    1

n=2, res=2
    2   1
   1     2

n=3, res=5
    3       3       2       1       1
  2       1       1   3       3       2
1           2               2           3


為什麼會這樣? 可以把tree想成一個棋盤方格
左下是root, 往上或往右分別代表兩個children path
這也是卡塔蘭數的例子

n=1
.

n=2
        .
        |
.---.   .

n=3
                .
                |
.---.---.   .---.   

.
|
.           .---.
|           |
.           .


.
|
.---.

*/