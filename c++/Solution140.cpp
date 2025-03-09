//DP Backtracking O(n * 2^n) O(n * 2^n), n = s.length()
class Solution140 {
public:
    vector<string> wordBreak(string s, vector<string>& word_dict) {
        // 這個memo可以把它看成是DP的一種
        unordered_map<string, vector<string>> memo; // <s, <s可以由那些word_dict組成...>>
        return backtracking(s, word_dict, memo);
    }

    vector<string> backtracking(string s, vector<string>& word_dict, unordered_map<string, vector<string>>& memo) {
        if (s.empty()) return {""}; // 走到底, 空字串只能由空字串組成
        if (memo.count(s)) return memo[s]; // 透過memo剪枝已知結果

        vector<string> res;
        for (string word : word_dict) {
            int len = word.length();
            if (s.length() < len || s.substr(0, len) != word) continue;

            vector<string> subs = backtracking(s.substr(len), word_dict, memo);
            for (string sub : subs) {
                string sentence = word;
                if (!sub.empty()) sentence += " " + sub;

                res.push_back(sentence);
            }
        }

        memo[s] = res;
        return memo[s];
    }
};