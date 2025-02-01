//DFS O(V + E) O(V + E), V = n, E = dislikes.size()
class Solution886 {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        unordered_map<int, vector<int>> edges; // <某人, <不喜歡的某甲, 某乙...>>
        for (vector<int> dislike : dislikes) {
            edges[dislike[0]].push_back(dislike[1]); // 要雙向判斷
            edges[dislike[1]].push_back(dislike[0]);
        }

        vector<int> colors(n + 1); // 0:未分組, 1/-1:兩組, 題目設定是[1, n], 0 不使用
        for (int i = 1; i <= n; ++i) {
            if (colors[i] == 0 && !dfs(i, 1, colors, edges)) {
                return false;
            }
        }
        return true;
    }

    bool dfs(int i, int color, vector<int>& colors, unordered_map<int, vector<int>>& edges) {
        if (colors[i] != 0) return colors[i] == color;

        colors[i] = color;
        for (int child : edges[i]) {
            if (!dfs(child, color * -1, colors, edges)) {
                return false;
            }
        }
        return true;
    }
};