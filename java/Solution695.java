package leetCode.java;

//DFS O(mn) O(mn)
class Solution695 {
    public int maxAreaOfIsland(int[][] grid) {
        //Return the maximum area of an island in grid
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                res = Math.max(res, dfs(i, j, grid));
            }
        }
        return res;
    }

    public int dfs(int i, int j, int[][] grid){
        if(grid[i][j] != 1) return 0;

        grid[i][j] = -1;//visited

        int m = grid.length;
        int n = grid[0].length;
        int count = 1;
        if(i + 1 <  m) count += dfs(i + 1, j, grid);
        if(i - 1 >= 0) count += dfs(i - 1, j, grid);
        if(j + 1 <  n) count += dfs(i, j + 1, grid);
        if(j - 1 >= 0) count += dfs(i, j - 1, grid);
        return count;
    }
}