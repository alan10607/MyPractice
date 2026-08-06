package leetCode.java;

import java.util.*;

//Backtracking O(n!) O(n^2)
class Solution51 {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        Set<Integer> col = new HashSet<>();
        Set<Integer> sum = new HashSet<>();
        Set<Integer> sub = new HashSet<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        backtracking(0, col, sum, sub, board);
        return res;
    }

    public void backtracking(int i, Set<Integer> col, Set<Integer> sum, Set<Integer> sub, char[][] board) {
        if (i == board.length) {
            res.add(buildBoard(board));
            return;
        }

        // for loop 處理當前row的皇后要放在哪個 col, backtracking 處理下一個 row
        for (int j = 0; j < board.length; ++j) {
            if (col.contains(j) || sum.contains(i + j) || sub.contains(i - j)) {
                continue;
            }

            col.add(j);
            sum.add(i + j);
            sub.add(i - j);
            board[i][j] = 'Q';
            backtracking(i + 1, col, sum, sub, board);
            board[i][j] = '.';
            col.remove(j);
            sum.remove(i + j);
            sub.remove(i - j);
        }
    }

    private List<String> buildBoard(char[][] board) {
        List<String> newBoard = new ArrayList<>();
        for (char[] row : board) {
            newBoard.add(new String(row));
        }
        return newBoard;
    }
}
/*

i+j => /
        0   1   2   3

0       0   1   2   3
1       1   2   3   4
2       2   3   4   5
3       3   4   5   6


i-j => \
        0   1   2   3

0       0   -1  -2  -3
1       1   0   -1  -2
2       2   1   0   -1
3       3   2   1   0


*/