//Backtracking O((4^n)/(n^(1/2))) O((4^n)/(n^(1/2))), Catalan number 卡塔蘭數, Cn = (2n)!/((n+1)!n!) = (4^n)/(n^(1/2))
class Solution22 {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        backtracking(n, 0, "", res);
        return res;
    }

    void backtracking(int n, int balance, string str, vector<string>& res) {
        if (n == 0 && balance == 0) {
            res.push_back(str);
            return;
        }
        if (n < 0 || balance < 0) return; // balance < 0表示左括號太多了

        backtracking(n - 1, balance + 1, str + "(", res);
        backtracking(n, balance - 1, str + ")", res);
    }
};