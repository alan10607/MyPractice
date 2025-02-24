//BFS O(V) O(V), V是所有狀態可能, 本題有六格, 共6!種狀態
class Solution773 {
public:
    int slidingPuzzle(vector<vector<int>>& board) {
        // board轉成string形式, 方便移動跟判斷
        string start = "";
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                start += to_string(board[i][j]);
            }
        }

        // 各相鄰格
        vector<vector<int>> neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

        int res = 0;
        unordered_set<string> visited;
        queue<string> q;
        q.push(start);
        while (!q.empty()) {
            for (int i = q.size(); i > 0; --i) {
                string node = q.front(); q.pop();
                if (visited.count(node)) continue;
                if (node == "123450") return res;

                visited.insert(node);

                // 找到0的index, 把0與相鄰格換位置窮舉
                for (int i = 0; i < node.size(); ++i) {
                    if (node[i] == '0') {
                        for (int neighbor : neighbors[i]) {
                            string next = node; // 記得clone一個
                            swap(next[i], next[neighbor]);
                            q.push(next);
                        }
                        break;
                    }
                }
            }

            ++res;
        }

        return -1;
    }
};

/*  由左而右, 由上而下記錄成string

0   1   2
3   4   5


neighbors:
0: 1,3
1: 0,2,4
2: 1,5
3: 0,4
4: 1,3,5
5: 2,4

*/