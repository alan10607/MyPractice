//Backtracking O(n * n!) O(n)
class Solution77 {
public:
    vector<vector<int>> res;
    
    vector<vector<int>> combine(int n, int k) {
        vector<int> selected;
        backtracking(1, selected, n, k);
        return res;
    }

    void backtracking(int start, vector<int>& selected, int n, int k) {
        if (selected.size() == k) {
            res.push_back(selected);
        }

        for (int i = start; i <= n; ++i) {
            selected.push_back(i);
            backtracking(i + 1, selected, n, k);
            selected.pop_back();
        }
    }
};