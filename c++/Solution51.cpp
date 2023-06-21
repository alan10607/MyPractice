//Backtracking O(n!) O(n^2)
class Solution51 {
public:
    vector<vector<string>> res;

    vector<vector<string>> solveNQueens(int n) {
        unordered_set<int> col;
        unordered_set<int> sum;
        unordered_set<int> sub;
        vector<string> board(n, string(n, '.'));
        backtracking(0, board, col, sum, sub);//以行為單位遍歷
        return res;
    }

    void backtracking(int i, vector<string>& board, unordered_set<int>& col, unordered_set<int>& sum, unordered_set<int>& sub){
        if(i == board.size()){          
            res.push_back(board);
            return;
        }

        for(int j=0; j<board.size(); ++j){//迴圈跑一行, 遞迴在跑所有行
            if(col.count(j) || sum.count(i + j) || sub.count(i - j)) continue;

            col.insert(j);
            sum.insert(i + j);
            sub.insert(i - j);
            board[i][j] = 'Q';
            backtracking(i + 1, board, col, sum, sub);
            col.erase(j);
            sum.erase(i + j);
            sub.erase(i - j);
            board[i][j] = '.';
        }
    }
};
/*
i+j => /
    0   1   2   3
0   0   1   2   3
1   1   2   3   4
2   2   3   4   5
3   3   4   5   6

i-j => \
    0   1   2   3
0   0   -1  -2  -3
1   1   0   -1  -2
2   2   1   0   -1
3   3   2   1   0
*/