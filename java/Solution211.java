package leetCode.java;

//Trie WordDictionary(): O(1) O(1), addWord(): O(n) O(V), search(): O(nZ) O(V)
//n = word.length(), V為node數量, Z = 26, search()時若遇到'.'需要跑for迴圈O(Z), 總共O(nZ)
class WordDictionary {//Solution211
    public WordDictionary[] children;
    public boolean endFlag;

    public WordDictionary() {
        children = new WordDictionary[26];
        endFlag = false;
    }

    public void addWord(String word) {
        WordDictionary root = this;
        for(char ch : word.toCharArray()){
            int index = ch - 'a';
            if(root.children[index] == null)
                root.children[index] = new WordDictionary();

            root = root.children[index];
        }
        root.endFlag = true;
    }

    public boolean search(String word) {
        return search(0, word, this);//遞迴代替for
    }

    public boolean search(int index, String word, WordDictionary wd) {
        if(index == word.length()) return wd.endFlag;//走到最後

        char ch = word.charAt(index);
        if(ch == '.'){
            for(WordDictionary child : wd.children){
                if(child != null && search(index + 1, word, child))
                    return true;//只要有一條通就true
            }
            return false;
        }else{
            WordDictionary child = wd.children[ch - 'a'];
            if(child == null) return false;
            return search(index + 1, word, child);
        }
    }
}