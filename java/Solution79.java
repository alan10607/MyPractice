package leetCode.java;

//DFS O(mn * 3L) O(mn), L = word.length(), L為最長word.length(), 除第一次外每次進入dfs有3種(不含自己)的方向, 需要進mn次dfs, 故為mn3^L
class Solution79 {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(dfs(i, j, 0, board, visited, word))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(int i, int j, int k, char[][] board, boolean[][] visited, String word){
        if(visited[i][j]) return false;
        if(board[i][j] != word.charAt(k)) return false;
        if(k == word.length() - 1) return true;

        visited[i][j] = true;

        int m = board.length;
        int n = board[0].length;
        if(i + 1 <  m && dfs(i + 1, j, k + 1, board, visited, word)) return true;
        if(i - 1 >= 0 && dfs(i - 1, j, k + 1, board, visited, word)) return true;
        if(j + 1 <  n && dfs(i, j + 1, k + 1, board, visited, word)) return true;
        if(j - 1 >= 0 && dfs(i, j - 1, k + 1, board, visited, word)) return true;

        visited[i][j] = false;

        return false;
    }
}