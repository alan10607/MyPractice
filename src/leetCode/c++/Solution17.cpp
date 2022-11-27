//Backtracking O(3^m 4^n) O(m + n), m, n代表digits中3或4個字母的數字個數
class Solution17 {
public:
    vector<string> res;

    vector<string> letterCombinations(string digits) {
        if(digits.empty()) return {};

        vector<string> letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtracking(0, "", digits, letters);
        return res;
    }

    void backtracking(int i, string str, string& digits, vector<string>& letters){
        if(str.length() == digits.length()){
            res.push_back(str);
            return;
        }

        string letter = letters[digits[i] - '0'];
        for(char ch : letter)
            backtracking(i + 1, str + ch, digits, letters);
    }
};