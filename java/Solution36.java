package leetCode.java;

//O(1) O(1), 數獨大小為固定
class Solution36 {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][][] box = new boolean[3][3][9];

        for (char i = 0; i < 9; i++) { 
            for (char j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;

                int num = board[i][j] - '1'; // 數獨從1開始
                if (row[i][num] || col[j][num] || box[i / 3][j / 3][num]) {
                    return false;
                }

                row[i][num] = true;
                col[j][num] = true;
                box[i / 3][j / 3][num] = true;
            }
        }
        return true;
    }
}

