package leetCode.java;

//DFS O(mn) O(mn)
class Solution733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        dfs(sr, sc, image, image[sr][sc], color);
        return image;
    }

    public void dfs(int i, int j, int[][] image, int color, int newColor) {
        int m = image.length;
        int n = image[0].length;
        if(i < 0 || i >= m || j < 0 || j >= n || image[i][j] != color || image[i][j] == newColor) return;

        image[i][j] = newColor;
        dfs(i + 1, j, image, color, newColor);
        dfs(i - 1, j, image, color, newColor);
        dfs(i, j + 1, image, color, newColor);
        dfs(i, j - 1, image, color, newColor);
    }
}