//Stack O(n) O(Z), n = s.length(), Z為字母數=26
class Solution1081 {
public:
    string smallestSubsequence(string s) { // 本題與316完全相同
        vector<int> cnt(26); // 字母出現次數
        for (char ch : s) {
            ++cnt[ch - 'a'];
        }

        string res;
        vector<bool> visited(26);
        for (char ch : s) {
            --cnt[ch - 'a'];
            if (visited[ch - 'a']) continue; // 已經有這個字母就跳過

            // 前一個字母比較大, 而且後面還有該字母, 就改用後面字母
            while (!res.empty() && res.back() > ch && cnt[res.back() - 'a'] > 0) {
                visited[res.back() - 'a'] = false;
                res.pop_back();
            }

            visited[ch - 'a'] = true;
            res.push_back(ch);
        }

        return res;
    }
};