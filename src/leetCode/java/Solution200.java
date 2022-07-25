package leetCode.java;

//DFS O(mn) O(mn)
class Solution200 {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == '1'){
                    res++;
                    dfs(i, j, grid);//相連的變成*
                }
            }
        }
        return res;
    }

    public void dfs(int i, int j, char[][] grid){
        if(grid[i][j] != '1') return;

        grid[i][j] = '*';

        int m = grid.length;
        int n = grid[0].length;
        if(i + 1 <  m) dfs(i + 1, j, grid);
        if(i - 1 >= 0) dfs(i - 1, j, grid);
        if(j + 1 <  n) dfs(i, j + 1, grid);
        if(j - 1 >= 0) dfs(i, j - 1, grid);
        return;
    }
}