//36 O(1) O(1), 數獨大小為固定
class Solution36 {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        vector<vector<bool>> row(9, vector<bool>(9));
        vector<vector<bool>> col(9, vector<bool>(9));
        vector<vector<vector<bool>>> box(3, vector<vector<bool>>(3, vector<bool>(9)));
        for(int i=0; i<9; ++i){
            for(int j=0; j<9; ++j){
                if(board[i][j] == '.') continue;

                int num = board[i][j] - '1';//數獨從1開始
                if(row[i][num] || col[j][num] || box[i / 3][j / 3][num])
                    return false;

                row[i][num] = true;
                col[j][num] = true;
                box[i / 3][j / 3][num] = true;
            }
        }
        return true;
    }
};

