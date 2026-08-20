package leetCode.java;

//Trie WordDictionary(): O(1) O(1), addWord(): O(n) O(V), search(): O(nZ) O(V)
//n = word.length(), V為node數量, Z = 26, search()時若遇到'.'需要跑for迴圈O(Z), 總共O(nZ)
class WordDictionary {//Solution211
    WordDictionary[] children;
    boolean isEnd;

    public WordDictionary() {
        children = new WordDictionary[26];
        isEnd = false;
    }
    
    public void addWord(String word) {
        WordDictionary root = this;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (root.children[index] == null) {
                root.children[index] = new WordDictionary();
            }
            root = root.children[index];
        }
        root.isEnd = true;
    }
    
    public boolean search(String word) {
        return search(word, 0, this);
    }

    public boolean search(String word, int index, WordDictionary root) {
        if (index == word.length()) {
            return root.isEnd;
        }

        char ch = word.charAt(index);
        if (ch  == '.') {
            for (WordDictionary child : root.children) { // 檢測是否有任何一個child
                if (child != null && search(word, index + 1, child)) {
                    return true; // 只要有一條通就true
                }
            }
            return false;
        } else {
            WordDictionary child = root.children[ch - 'a'];
            if (child == null) {
                return false;
            }
            return search(word, index + 1, child);
        }
    }
}