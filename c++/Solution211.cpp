//Trie WordDictionary(): O(1) O(1), addWord(): O(n) O(V), search(): O(nZ) O(V)
//n = word.length(), V為node數量, Z = 26, search()時若遇到'.'需要跑for迴圈O(Z), 總共O(nZ)
class WordDictionary {//Solution211
public:
    vector<WordDictionary*> children;
    bool end_flag;

    WordDictionary() {
        children = vector<WordDictionary*>(26, nullptr);
        end_flag = false;
    }

    void addWord(string word) {
        WordDictionary* root = this;
        for (char ch : word) {
            if (!root->children[ch - 'a']) {
                root->children[ch - 'a'] = new WordDictionary();
            }
            root = root->children[ch - 'a'];
        }
        root->end_flag = true;
    }

    bool search(string word) {
        return search(0, word, this); 
    }

    bool search(int i, string word, WordDictionary* root) {
        if (i == word.length()) return root->end_flag; // 到底了

        char ch = word[i];
        if (ch == '.') {
            for (WordDictionary* child : root->children) {
                if (child && search(i + 1, word, child)) {
                    return true; // 其中有就true
                }
            }
            return false;
        } else {
            if (!root->children[ch - 'a']) {
                return false;
            } else {
                return search(i + 1, word, root->children[ch - 'a']);
            }
        }
    }
};