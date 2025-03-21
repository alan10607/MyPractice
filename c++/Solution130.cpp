//DFS O(mn) O(mn)
class Solution130 {
public:
    void solve(vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        for (int i = 0; i < m; ++i) {
            dfs(i, 0, board);
            dfs(i, n - 1, board);
        }
        for (int j = 0; j < n; ++j) {
            dfs(0, j, board);
            dfs(m - 1, j, board);
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '*') { // *代表存活的O
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') { // 沒被掃瞄到的O代表與邊框不相連
                    board[i][j] = 'X';
                }
            }
        }
    }

    void dfs(int i, int j, vector<vector<char>>& board) {
        int m = board.size(), n = board[0].size();
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') return;

        board[i][j] = '*'; // 先把O改成*
        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (pair<int, int> dir : dirs) {
            dfs(i + dir.first, j + dir.second, board);
        }
    }
};