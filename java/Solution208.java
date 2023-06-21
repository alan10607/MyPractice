package leetCode.java;

//Trie Trie(): O(1) O(1), insert(), search(): O(n) O(V), n = word.length(), V為node數量
class Trie {//Solution208
    public Trie[] children;
    public boolean endFlag;

    public Trie() {
        children = new Trie[26];
        endFlag = false;
    }

    public void insert(String word) {
        Trie root = this;
        for(char ch : word.toCharArray()){
            int index = ch - 'a';
            if(root.children[index] == null)
                root.children[index] = new Trie();

            root = root.children[index];
        }
        root.endFlag = true;
    }

    public boolean search(String word) {
        Trie root = this;
        for(char ch : word.toCharArray()){
            int index = ch - 'a';
            if(root.children[index] == null)
                return false;

            root = root.children[index];
        }
        return root.endFlag;
    }

    public boolean startsWith(String prefix) {
        Trie root = this;
        for(char ch : prefix.toCharArray()){
            int index = ch - 'a';
            if(root.children[index] == null)
                return false;

            root = root.children[index];
        }
        return true;
    }
}