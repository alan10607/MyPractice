//DFS O(mn) O(mn)
class Solution733 {
public:
    vector<vector<int>> floodFill(vector<vector<int>>& image, int sr, int sc, int color) {
        dfs(sr, sc, image, image[sr][sc], color);
        return image;
    }

    void dfs(int i, int j, vector<vector<int>>& image, int color, int newColor){
        int m = image.size(), n = image[0].size();
        if(i < 0 || i >= m || j < 0 || j >= n || image[i][j] != color || image[i][j] == newColor) return;

        image[i][j] = newColor;

        dfs(i + 1, j, image, color, newColor);
        dfs(i - 1, j, image, color, newColor);
        dfs(i, j + 1, image, color, newColor);
        dfs(i, j - 1, image, color, newColor);
    }
};