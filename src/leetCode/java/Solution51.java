package leetCode.java;

import java.util.*;

//Backtracking O(n!) O(n^2)
class Solution51 {
    public List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        Set<Integer> col = new HashSet<>();
        Set<Integer> sum = new HashSet<>();
        Set<Integer> sub = new HashSet<>();
        char[][] board = new char[n][n];
        for(char[] line : board)
            Arrays.fill(line, '.');

        backtracking(0, col, sum, sub, board);
        return res;
    }

    public void backtracking(int m, Set<Integer> col, Set<Integer> sum, Set<Integer> sub, char[][] board){
        if(m == board.length){//走完全部代表有解
            res.add(printBoard(board));
        }

        //遞迴遍歷橫列m, for遍歷直行n
        for(int n=0; n<board.length; n++){
            if(!col.contains(n) && !sum.contains(m + n) && !sub.contains(m - n)){
                col.add(n);
                sum.add(m + n);
                sub.add(m - n);
                board[m][n] = 'Q';
                backtracking(m + 1, col, sum, sub, board);
                col.remove(n);
                sum.remove(m + n);
                sub.remove(m - n);
                board[m][n] = '.';
            }
        }
    }

    public List<String> printBoard(char[][] board){
        List<String> newBoard = new ArrayList<>();
        for(char[] line : board)
            newBoard.add(new String(line));

        return newBoard;
    }
}
/*
m + n => /
	0	1	2	3
0	0	1	2	3
1	1	2	3	4
2	2	3	4	5
3	3	4	5	6

m - n => \
	0	1	2	3
0	0  -1  -2  -3
1	1	0  -1  -2
2   2	1	0  -1
3	3	2	1	0

*/