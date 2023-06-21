//Backtracking O((4^n)/(n^(1/2))) O((4^n)/(n^(1/2))), Catalan number 卡塔蘭數, Cn = (2n)!/((n+1)!n!) = (4^n)/(n^(1/2))
class Solution22 {
public:
    vector<string> res;

    vector<string> generateParenthesis(int n) {
        backtracking(0, "", n);
        return res;
    }

    void backtracking(int level, string str, int n){
        if(n == 0 && level == 0){//level=(個數 - )個數
            res.push_back(str);
            return;
        }
        if(n < 0 || level < 0) return;

        backtracking(level + 1, str + "(", n - 1);
        backtracking(level - 1, str + ")", n);
    }
};