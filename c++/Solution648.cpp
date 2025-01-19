//Trie O(d + s) O (d + s), d為dictionary總char數量, s = sentence.length()
class Solution648 {
public:
    class Trie {
    public:
        vector<Trie*> children;
        bool end_flag;
        Trie() : end_flag(false), children(vector<Trie*>(26, nullptr)) {};

        void insert(string word) {
            Trie* root = this;
            for (char ch : word) {
                if (!root->children[ch - 'a']) {
                    root->children[ch - 'a'] = new Trie();
                }
                root = root->children[ch - 'a'];
            }
            root->end_flag = true;
        }

        string findPrefix(string word) {
            Trie* root = this;
            string prefix = "";
            for (char ch : word) {
                if (root->end_flag) {
                    return prefix; // 找到end就回傳
                }

                prefix.push_back(ch);
                if (!root->children[ch - 'a']) {
                    break;
                }

                root = root->children[ch - 'a'];
            }
            return word; // 沒有找到prefix, 回傳自己本身
        }
    };

    string replaceWords(vector<string>& dictionary, string sentence) {
        Trie trie;
        for (string word : dictionary) {
            trie.insert(word);
        }

        vector<string> words = split(sentence);
        string res = "";
        for (string word : words) {
            if (!res.empty()) res += " ";
            res += trie.findPrefix(word);
        }
        return res;
    }

    vector<string> split(string s) {
        vector<string> words;
        int l = 0, r = 0;
        while (r < s.length()) {
            if (s[r] == ' ') {
                words.push_back(s.substr(l, r - l)); // 範圍[l, r)
                l = r + 1;
            }
            ++r;
        }
        words.push_back(s.substr(l)); // 記得最後

        return words;
    }
};