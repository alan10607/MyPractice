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


//Union-Find O(ElogE) O(V)
class Solution886_2 {
public:
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        unordered_map<int, vector<int>> edges;
        for (vector<int> dislike : dislikes) {
            edges[dislike[0]].push_back(dislike[1]);
            edges[dislike[1]].push_back(dislike[0]);
        }

        vector<int> parents(n + 1); // 題目範圍是[1,n], 0不使用
        for (int i = 1; i <=n ; ++i) { // 初始成-1會有parents[node]=node造成recursive問題
            parents[i] = i;
        }
        for (int i = 1; i <= n; ++i) {
            if (!edges.count(i)) continue; // 排除不相連的node

            int a = find(i, parents);
            int tmp;
            for (int j = 0; j < edges[i].size(); ++j) {
                int b = find(edges[i][j], parents);

                if (a == b) return false; // 與下一個應該要不同組

                if (j == 0) {
                    tmp = b; // 先抓一個當作parent
                } else {
                    parents[b] = tmp;
                }
            }
        }
        return true;
    }

    int find(int node, vector<int>& parents) {
        if (parents[node] == node) return node;
        return parents[node] = find(parents[node], parents);
    }
};
/*
這題也可以用Union Find, 把二向圖分成兩組


a----b(tmp)
|
|----b'
|
+----b''

*/