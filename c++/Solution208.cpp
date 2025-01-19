//Trie Trie(): O(1) O(1), insert(), search(): O(n) O(V), n = word.length(), V為node數量
class Trie {//Solution208
public:
    vector<Trie*> children;
    bool end_flag;

    Trie() {
        children = vector<Trie*>(26, nullptr);
        end_flag = false;
    }

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

    bool search(string word) {
        Trie* root = this;
        for (char ch : word) {
            if (!root->children[ch - 'a']) {
                return false;
            }
            root = root->children[ch - 'a'];
        }
        return root->end_flag;
    }

    bool startsWith(string prefix) {
        Trie* root = this;
        for (char ch : prefix) {
            if (!root->children[ch - 'a']) {
                return false;
            }
            root = root->children[ch - 'a'];
        }
        return true;
    }
};