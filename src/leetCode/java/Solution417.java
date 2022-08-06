package leetCode.java;

import java.util.*;

//DFS O(mn) O(mn)
class Solution417 {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for(int i=0; i<m; i++)
            dfs(i, 0, pacific, -1, heights);

        for(int j=0; j<n; j++)
            dfs(0, j, pacific, -1, heights);

        for(int i=0; i<m; i++)
            dfs(i, n - 1, atlantic, -1, heights);

        for(int j=0; j<n; j++)
            dfs(m - 1, j, atlantic, -1, heights);

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(pacific[i][j] && atlantic[i][j]){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    res.add(temp);
                }
            }
        }
        return res;
    }

    public void dfs(int i, int j, boolean[][] ocean, int last, int[][] heights){
        if(ocean[i][j]) return;//已經visited
        if(last > heights[i][j]) return;//水只往低處流, 可以相等

        ocean[i][j] = true;

        int m = heights.length;
        int n = heights[0].length;
        if(i + 1 <  m) dfs(i + 1, j, ocean, heights[i][j], heights);
        if(i - 1 >= 0) dfs(i - 1, j, ocean, heights[i][j], heights);
        if(j + 1 <  n) dfs(i, j + 1, ocean, heights[i][j], heights);
        if(j - 1 >= 0) dfs(i, j - 1, ocean, heights[i][j], heights);
        return;
    }
}
/*
			n
	p	p	p	p	p
	p	1	2	3	a
m	p	4	5	6	a
	p	7	8	9	a
	a	a	a	a	a
*/