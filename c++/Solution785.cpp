//DFS O(V + E) O(V)
class Solution785 {
public:
    bool isBipartite(vector<vector<int>>& graph) {
        vector<int> colors(graph.size()); // 0:unvisited, 1/-1:代表兩色
        for (int i = 0; i < graph.size(); ++i) {
            if (colors[i] == 0 && !dfs(i, 1, colors, graph)) { // 有可能存在獨立的node, 所以要找到未染色的node看
                return false;
            }
        }
        return true;
    }

    bool dfs(int i, int color, vector<int>& colors, vector<vector<int>>& graph) {
        if (colors[i] != 0) return colors[i] == color; // 不同色代表不是二分圖

        colors[i] = color;
        for (int child : graph[i]) {
            if (!dfs(child, color * -1, colors, graph)) {
                return false; // 檢測任意子圖不是二分圖就返回
            }
        }
        return true;
    }
};