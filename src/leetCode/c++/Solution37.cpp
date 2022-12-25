//DFS O(1) O(1)
class Solution37 {
public:
    void solveSudoku(vector<vector<char>>& board) {
        vector<vector<bool>> row(9, vector<bool>(9));
        vector<vector<bool>> col(9, vector<bool>(9));
        vector<vector<vector<bool>>> box(3, vector<vector<bool>>(3, vector<bool>(9)));
        for(int i=0; i<9; ++i){//先標記避免在dfs迴圈查詢
            for(int j=0; j<9; ++j){
                if(board[i][j] != '.'){
                    int num = board[i][j] - '1';
                    row[i][num] = col[j][num] = box[i / 3][j / 3][num] = true;
                }
            }
        }

        dfs(0, 0, board, row, col, box);//從橫軸開始依序檢驗,檢驗完一排就往下
    }

    bool dfs(int i, int j, vector<vector<char>>& board, vector<vector<bool>>& row, vector<vector<bool>>& col, vector<vector<vector<bool>>>& box){
        if(i == 9) return true;//已檢驗完全部
        if(j == 9) return dfs(i + 1, 0, board, row, col, box);
        if(board[i][j] != '.') return dfs(i, j + 1, board, row, col, box);

        for(char ch = '1'; ch <= '9'; ++ch){
            int num = ch - '1';
            if(row[i][num] || col[j][num] || box[i / 3][j / 3][num])
                continue;//已重複, 跳過

            row[i][num] = col[j][num] = box[i / 3][j / 3][num] = true;
            board[i][j] = ch;

            if(dfs(i, j + 1, board, row, col, box))
                return true;

            board[i][j] = '.';
            row[i][num] = col[j][num] = box[i / 3][j / 3][num] = false;
        }
        return false;
    }
};