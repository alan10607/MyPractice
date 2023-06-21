//Backtracking O(n * 2^k) O(n), n = s.length(), k為s中為字母的長度
class Solution784 {
public:
    vector<string> res;

    vector<string> letterCasePermutation(string s) {
        backtracking(0, s);
        return res;
    }

    void backtracking(int i, string s){
        if(i == s.length()){
            res.push_back(s);
            return;
        }
        if(s[i] >= '0' && s[i] <= '9'){
            backtracking(i + 1, s);//跳過
            return;
        }

        backtracking(i + 1, s);

        s[i] ^= 32;//A(97), a(65)剛好差32(10000)
        backtracking(i + 1, s);
    }
};

/* s = "a1b2"
                []
        a               A
        a1              A1
    a1b    a1B      A1b    A1B
    a1b2   a1B2     A1b2   A1B2

*/