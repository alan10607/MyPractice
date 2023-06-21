package leetCode.java;

//DFS O(1) O(1)
class Solution37 {
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][][] box = new boolean[3][3][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '1';
                    row[i][num] = col[j][num] = box[i / 3][j / 3][num] = true;
                }
            }
        }

        dfs(0, 0, board, row, col, box);
    }

    public boolean dfs(int i, int j, char[][] board, boolean[][] row, boolean[][] col, boolean[][][] box){
        if(i == 9) return true;
        if(j == 9) return dfs(i + 1, 0, board, row, col, box);
        if(board[i][j] != '.') return dfs(i, j + 1, board, row, col, box);

        for(char ch = '1'; ch <= '9'; ch++){
            int num = ch - '1';
            if(row[i][num] || col[j][num] || box[i / 3][j / 3][num])
                continue;

            row[i][num] = col[j][num] = box[i / 3][j / 3][num] = true;
            board[i][j] = ch;

            if(dfs(i, j + 1, board, row, col, box))
                return true;

            board[i][j] = '.';
            row[i][num] = col[j][num] = box[i / 3][j / 3][num] = false;
        }
        return false;
    }
}