package leetCode.java;

import java.util.*;

//DFS O(mn) O(mn)
class Solution417 {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            dfs(heights, pac, i, 0, -1);
        }
        for (int j = 0; j < n; ++j) {
            dfs(heights, pac, 0, j, -1);
        }
        for (int i = 0; i < m; ++i) {
            dfs(heights, atl, i, n - 1, -1);
        }
        for (int j = 0; j < n; ++j) {
            dfs(heights, atl, m - 1, j, -1);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pac[i][j] && atl[i][j]) {
                    res.add(List.of(i, j));
                }
            }
        }
        return res;
    }

    public void dfs(int[][] heights, boolean[][] ocean, int i, int j, int lastHeight) {
        int m = heights.length, n = heights[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || heights[i][j] < lastHeight || ocean[i][j]) {
            return; // 出界, 低於上一個, 已經visited
        }
        ocean[i][j] = true;
        for (int[] dir : dirs) {
            dfs(heights, ocean, i + dir[0], j + dir[1], heights[i][j]);
        }
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