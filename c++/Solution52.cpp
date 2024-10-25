//Backtracking O(n!) O(n^2)
class Solution52 {
public:
    int totalNQueens(int n) {
        vector<string> board(n, string(n, '.'));
        unordered_set<int> col;
        unordered_set<int> sum;
        unordered_set<int> sub;
        int res = 0; // 記得參數是int&
        backtracking(0, res, col, sum, sub, board); // 解法同Solution 51
        return res;
    }

    void backtracking(int i, int& res, unordered_set<int>& col, unordered_set<int>& sum, unordered_set<int>& sub, vector<string> board) {
        if (i == board.size()) {
            ++res;
            return;
        }

        for (int j = 0; j < board.size(); ++j) {
            if (col.count(j) || sum.count(i + j) || sub.count(i - j)) continue;

            col.insert(j);
            sum.insert(i + j);
            sub.insert(i - j);
            backtracking(i + 1, res, col, sum, sub, board);
            col.erase(j);
            sum.erase(i + j);
            sub.erase(i - j);
        }
    }
};