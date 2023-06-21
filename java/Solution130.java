package leetCode.java;

//DFS O(mn) O(mn), 在最壞情況遍歷所有格子, 空間複雜度為遞迴次數即O(mn)
class Solution130 {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0; i<m; i++){
            dfs(i, 0, board);
            dfs(i, n - 1, board);
        }

        for(int j=0; j<n; j++){
            dfs(0, j, board);
            dfs(m - 1, j, board);
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == '.'){
                    board[i][j] = 'O';
                }else if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(int i, int j, char[][] board){
        if(board[i][j] != 'O') return;

        board[i][j] = '.';

        int m = board.length;
        int n = board[0].length;
        if(i + 1 <  m) dfs(i + 1, j, board);
        if(i - 1 >= 0) dfs(i - 1, j, board);
        if(j + 1 <  n) dfs(i, j + 1, board);
        if(j - 1 >= 0) dfs(i, j - 1, board);
    }
}
/*
			n
	(0,0)		(0,n-1)
m
	(m-1,0)		(m-1,n-1)
*/