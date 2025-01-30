//Stack O(n) O(Z), n = s.length(), Z為字母數=26
class Solution316 {
public:
    string removeDuplicateLetters(string s) {
        vector<int> cnt(26); // 字母出現次數
        for (char ch : s) {
            ++cnt[ch - 'a'];
        }

        string res; // 用string代替stack
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


//Stack O(n) O(Z), n = s.length(), Z為字母數=26
class Solution316_2 {
public:
    string removeDuplicateLetters(string s) { // 本題與1081完全相同
        vector<int> cnt(26); // 字母出現次數
        for (char ch : s) {
            ++cnt[ch - 'a'];
        }

        stack<char> st;
        vector<bool> visited(26);
        for (char ch : s) {
            --cnt[ch - 'a'];
            if (visited[ch - 'a']) continue; // 已經有這個字母就跳過

            // Monotonic Stack概念, 前一個字母比較大, 而且後面還有該字母, 就改用後面字母
            while (!st.empty() && st.top() > ch && cnt[st.top() - 'a'] > 0) {
                visited[st.top() - 'a'] = false;
                st.pop();
            }

            visited[ch - 'a'] = true;
            st.push(ch);
        }

        string res(st.size(), ' '); // string constructor要有兩個
        int i = st.size() - 1;
        while (!st.empty()) {
            res[i--] = st.top(); // 此時 st是反的
            st.pop();
        }
        return res;
    }
};
/*
ex:
s = dbcabc
st = [d]
st = [d,b]
st = [d,b,c]

進入while迴圈, 由於b,c都比a大, 而且後面都還有bc, 因此pop掉
st = [d,a]
st = [d,a,b]
st = [d,a,b,c]

res = dacb
*/