package leetCode.java;

//DFS O(mn) O(mn), 在最壞情況遍歷所有格子, 空間複雜度為遞迴次數即O(mn)
class Solution130 {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        // 對邊緣進行dfs標記, 之後打叉所有沒被標記的位置
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }
        for (int j = 0; j < n; ++j) {
            dfs(board, 0, j);
            dfs(board, m - 1, j);
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

    public void dfs(char[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
            return;
        }
        board[i][j] = '*'; // 先把O改成*
        for (int[] dir : dirs) {
            dfs(board, i + dir[0], j + dir[1]);
        }
    }
}
/*
			n
	(0,0)		(0,n-1)
m
	(m-1,0)		(m-1,n-1)
*/