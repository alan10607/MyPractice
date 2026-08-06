package leetCode.java;

//DFS O(mn * 3L) O(mn), L = word.length(), L為最長word.length(), 除第一次外每次進入dfs有3種(不含自己)的方向, 需要進mn次dfs, 故為mn3^L
class Solution79 {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (dfs(i, j, 0, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, int k, char[][] board, String word) {
        if (k == word.length()) {
            return true;
        }
        int m = board.length, n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(k)) {
            return false;
        }

        char tmp = board[i][j];
        board[i][j] = '#'; // 表示 visited, 這樣就不用visited的陣列
        for (int[] dir : dirs) {
            if (dfs(i + dir[0], j + dir[1], k + 1, board, word)) {
                return true;
            }
        }
        board[i][j] = tmp;

        return false;
    }
}