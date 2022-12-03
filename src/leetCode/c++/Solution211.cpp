//Trie WordDictionary(): O(1) O(1), addWord(): O(n) O(V), search(): O(nZ) O(V)
//n = word.length(), V為node數量, Z = 26, search()時若遇到'.'需要跑for迴圈O(Z), 總共O(nZ)
class WordDictionary {//Solution211
public:
    vector<WordDictionary*> children;
    bool endFlag;

    WordDictionary() {
        children = vector<WordDictionary*>(26, nullptr);
        endFlag = false;
    }

    void addWord(string word) {
        WordDictionary* wd = this;
        for(char ch : word){
            int index = ch - 'a';
            if(!wd->children[index])
                wd->children[index] = new WordDictionary();

            wd = wd->children[index];
        }
        wd->endFlag = true;
    }

    bool search(string word) {
        return search(0, this, word);
    }

    bool search(int i, WordDictionary* wd, string word){
        if(i == word.length()) return wd->endFlag;//到底了

        char ch = word[i];
        if(ch == '.'){
            for(WordDictionary* child : wd->children){
                if(child && search(i + 1, child, word))
                    return true;//其中有就true
            }
            return false;
        }else{
            if(!wd->children[ch - 'a'])
                return false;

            return search(i + 1, wd->children[ch - 'a'], word);
        }
    }
};