//DFS O(mn * 3L) O(mn), L = word.length(), L為最長word.length(), 除第一次外每次進入dfs有3種(不含自己)的方向, 需要進mn次dfs, 故為mn3^L
class Solution79 {
public:
    bool exist(vector<vector<char>>& board, string word) {
        int m = board.size(), n = board[0].size();
        vector<vector<bool>> visited(m, vector<bool>(n, false));
        for(int i=0; i<m; ++i){
            for(int j=0; j<n; ++j){
                if(dfs(i, j, 0, visited, board, word))
                    return true;
            }
        }
        return false;
    }

    bool dfs(int i, int j, int k, vector<vector<bool>>& visited, vector<vector<char>>& board, string& word){
        if(k == word.size()) return true;
        int m = board.size(), n = board[0].size();
        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || board[i][j] != word[k]) return false;

        visited[i][j] = true;

        vector<pair<int, int>> dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(auto dir : dirs){
            if(dfs(i + dir.first, j + dir.second, k + 1, visited, board, word))
                return true;
        }

        visited[i][j] = false;
        
        return false;
    }
};