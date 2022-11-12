//Trie Trie(): O(1) O(1), insert(), search(): O(n) O(V), n = word.length(), V為node數量
class Trie {//Solution208
public:
    Trie* children[26];
    bool endFlag;
    
    Trie() {
        for (auto &child : children)
            child = nullptr;
        
        endFlag = false;
    }
    
    void insert(string word) {
        Trie* root = this;
        for(char c : word){
            int index = c - 'a';
            if(!root->children[index])
                root->children[index] = new Trie();

            root = root->children[index];
        }
        root->endFlag = true;
    }
    
    bool search(string word) {
        Trie* root = this;
        for(char c : word){
            int index = c - 'a';
            if(!root->children[index])
                return false;

            root = root->children[index];
        }
        return root->endFlag;
    }
    
    bool startsWith(string prefix) {
	Trie* root = this;
        for(char c : prefix){
            int index = c - 'a';
            if(!root->children[index])
                return false;

            root = root->children[index];
        }
        return true;
    }
};